package shop.mtcoding.jobara.apply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.jobara.apply.dto.ApplyReq.ApplyDecideReqDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.CompanyApplyRespDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.EmployeeApplyRespDto;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class ApplyController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ApplyService applyService;

    @GetMapping("/board/{id}/apply")
    public ResponseEntity<?> apply(@PathVariable Integer id) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요한 기능입니다");
        if (!principal.getRole().equals("employee")) {
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        applyService.insertApply(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "지원 성공", null), HttpStatus.OK);
    }

    @GetMapping("/company/{id}/apply")
    public String companyApplyList(@PathVariable Integer id, Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        if (principal.getId() != id) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        List<CompanyApplyRespDto> applyListPS = applyService.getApplyForCompany(id);
        model.addAttribute("applyList", applyListPS);
        return "company/applyList";
    }

    @GetMapping("/employee/{id}/apply")
    public String employeeApplyList(@PathVariable Integer id, Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다");
        if (!principal.getRole().equals("employee")) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        if (principal.getId() != id) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        List<EmployeeApplyRespDto> applyListPS = applyService.getApplyForEmployee(id);
        model.addAttribute("applyList", applyListPS);
        return "employee/applyList";
    }

    @PutMapping("/apply/decide")
    public @ResponseBody ResponseEntity<?> decideApplyment(@RequestBody ApplyDecideReqDto applyDecideReqDto) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        Verify.validateApiObject(applyDecideReqDto.getUserId(), "처리할 유저 Id를 입력하세요.");
        Verify.validateApiObject(applyDecideReqDto.getState(), "처리할 결과 코드를 입력하세요.");
        applyService.approveApply(applyDecideReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "상태 변경 완료", null), HttpStatus.OK);
    }
}
