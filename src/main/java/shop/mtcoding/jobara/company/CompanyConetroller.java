package shop.mtcoding.jobara.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.aop.CompanyCheck;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.RedisServiceSet;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.company.dto.CompanyResp.CompanyUpdateRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
@RequiredArgsConstructor
public class CompanyConetroller {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisServiceSet redisServiceSet;

    @GetMapping("/company/joinForm")
    public String joinForm() {
        return "company/joinForm";
    }

    @GetMapping("/company/updateForm")
    @CompanyCheck
    public String updateForm(Model model) {
        UserVo principal = redisService.getValue("principal");
        CompanyUpdateRespDto companyUpdateRespDto = companyService.getCompanyUpdateRespDto(principal.getId());
        model.addAttribute("companyDto", companyUpdateRespDto);
        redisServiceSet.addModel(model);
        return "company/updateForm";
    }

    @PostMapping("/company/join")
    public String join(CompanyJoinReqDto companyJoinReqDto) {
        Verify.validateString(companyJoinReqDto.getUsername(), "유저네임을 입력하세요.");
        Verify.validateString(companyJoinReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(companyJoinReqDto.getEmail(), "이메일을 입력하세요.");
        companyService.insertCompany(companyJoinReqDto);
        return "redirect:/";
    }

    @PostMapping("/company/update")
    @CompanyCheck
    public String update(CompanyUpdateReqDto companyUpdateReqDto, MultipartFile profile) {

        UserVo principal = redisService.getValue("principal");

        if (profile.isEmpty()) {
            throw new CustomException("사진이 전송되지 않았습니다");
        }
        if (!profile.getContentType().startsWith("image")) {
            throw new CustomException("사진 파일만 업로드 할 수 있습니다.");
        }
        Verify.validateString(companyUpdateReqDto.getPassword(), "암호를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getEmail(), "이메일을 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyName(), "회사 이름을 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getAddress(), "주소를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getDetailAddress(), "상세 주소를 입력하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyScale(), "회사 규모란을 선택하세요.");
        Verify.validateString(companyUpdateReqDto.getCompanyField(), "회사 업종란을 선택하세요.");
        Verify.validateString(companyUpdateReqDto.getTel(), "전화번호를 입력하세요.");
        UserVo UserVoPS = companyService.updateCompany(companyUpdateReqDto, principal.getId(), profile);
        redisService.setValue("principal", UserVoPS);
        return "redirect:/";
    }
}
