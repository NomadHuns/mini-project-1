package shop.mtcoding.jobara.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.aop.EmployeeCheck;
import shop.mtcoding.jobara.common.aop.EmployeeCheckApi;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.RedisServiceSet;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeTechUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeUpdateRespDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisServiceSet redisServiceSet;

    @GetMapping("/employee/joinForm")
    public String joinForm() {
        return "employee/joinForm";
    }

    @GetMapping("/employee/loginForm")
    public String loginForm() {
        return "employee/loginForm";
    }

    @GetMapping("/employee/list")
    public String employeeList(Model model, Integer page) {
        UserVo principal = redisService.getValue("principal");
        PagingDto pagingPS = employeeService.getEmployee(page);
        model.addAttribute("pagingDto", pagingPS);
        if (principal != null) {
            if (principal.getRole().equals("company")) {
                List<EmployeeAndResumeRespDto> recommendEmployeeListPS = employeeService
                        .getRecommendEmployee(principal.getId());
                model.addAttribute("recommendEmployeeList", recommendEmployeeListPS);
            }
        }
        redisServiceSet.addModel(model);
        return "employee/list";
    }

    @GetMapping("/employee/{id}")
    public String employeeDetail(@PathVariable int id, Model model) {
        EmployeeAndResumeRespDto employeePS = employeeService.getEmployee(id);
        List<String> employeeTechPS = employeeService.getEmployeeTech(id);
        model.addAttribute("employee", employeePS);
        model.addAttribute("employeeTech", employeeTechPS);
        redisServiceSet.addModel(model);
        return "employee/detail";
    }

    @GetMapping("/employee/{id}/resume/{resumeId}")
    public String employeeDetail(@PathVariable int id, @PathVariable int resumeId, Model model) {
        EmployeeAndResumeRespDto employeePS = employeeService.getEmployee(id, resumeId);
        List<String> employeeTechPS = employeeService.getEmployeeTech(id);
        model.addAttribute("employee", employeePS);
        model.addAttribute("employeeTech", employeeTechPS);
        redisServiceSet.addModel(model);
        return "employee/detail";
    }

    @GetMapping("/employee/updateForm")
    @EmployeeCheck
    public String updateForm(Model model) {
        UserVo principal = redisService.getValue("principal");
        EmployeeUpdateRespDto employeeUpdateRespDto = employeeService.getEmployeeUpdateRespDto(principal.getId());
        List<Integer> employeeSkill = employeeService.getSkillForDetail(principal.getId());
        model.addAttribute("employeeDto", employeeUpdateRespDto);
        model.addAttribute("employeeSkill", employeeSkill);
        redisServiceSet.addModel(model);
        return "employee/updateForm";
    }

    @PostMapping("/employee/join")
    public String join(EmployeeJoinReqDto employeeJoinReqDto) {
        Verify.validateString(employeeJoinReqDto.getUsername(), "??????????????? ???????????????.");
        Verify.validateString(employeeJoinReqDto.getPassword(), "????????? ???????????????.");
        Verify.validateString(employeeJoinReqDto.getEmail(), "???????????? ???????????????.");
        employeeService.insertEmployee(employeeJoinReqDto);
        return "redirect:/";
    }

    @PostMapping("/employee/update/{id}")
    @EmployeeCheck
    public String update(EmployeeUpdateReqDto employeeUpdateReqDto, MultipartFile profile) {
        UserVo principal = redisService.getValue("principal");
        Verify.validateString(employeeUpdateReqDto.getPassword(), "????????? ???????????????.");
        Verify.validateString(employeeUpdateReqDto.getEmail(), "???????????? ???????????????.");
        Verify.validateString(employeeUpdateReqDto.getAddress(), "????????? ???????????????.");
        Verify.validateString(employeeUpdateReqDto.getDetailAddress(), "?????? ????????? ???????????????.");
        Verify.validateString(employeeUpdateReqDto.getTel(), "??????????????? ???????????????.");
        Verify.validateObject(employeeUpdateReqDto.getCareer(), "????????? ???????????????.");
        Verify.validateString(employeeUpdateReqDto.getEducation(), "????????? ???????????????.");

        if (profile.isEmpty()) {
            throw new CustomException("????????? ???????????? ???????????????");
        }
        if (!profile.getContentType().startsWith("image")) {
            throw new CustomException("?????? ????????? ????????? ??? ??? ????????????.");
        }

        UserVo UserVoPS = employeeService.updateEmpolyee(employeeUpdateReqDto, principal.getId(), profile);
        redisService.setValue("principal", UserVoPS);
        return "redirect:/";
    }

    @PutMapping("/employee/update/tech/{id}")
    @EmployeeCheckApi
    public ResponseEntity<?> update(@PathVariable int id,
            @RequestBody EmployeeTechUpdateReqDto employeeTechUpdateReqDto) {
        UserVo principal = redisService.getValue("principal");
        if (employeeTechUpdateReqDto.getCheckedValues() != null) {
            employeeService.updateEmpolyeeTech(employeeTechUpdateReqDto.getCheckedValues(), principal.getId());
        }

        return new ResponseEntity<>(new ResponseDto<>(1, " ????????????", null), HttpStatus.OK);
    }

}
