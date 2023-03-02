package shop.mtcoding.jobara.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.PathUtil;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeLoginReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeUpdateRespDto;
import shop.mtcoding.jobara.employee.model.Employee;
import shop.mtcoding.jobara.employee.model.EmployeeRepository;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;
import shop.mtcoding.jobara.user.vo.UserVo;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public List<EmployeeAndResumeRespDto> getEmployee() {
        List<EmployeeAndResumeRespDto> employeePS = employeeRepository.findAllWithResume();
        return employeePS;
    }

    public EmployeeAndResumeRespDto getEmployee(int id) {
        EmployeeAndResumeRespDto employeePS = employeeRepository.findEmployeeByIdWithResume(id);
        return employeePS;
    }

    public UserVo getEmployee(EmployeeLoginReqDto employeeLoginReqDto) {
        UserVo userVo = userRepository.findByUsernameAndPassword(
                new User(employeeLoginReqDto.getUsername(), employeeLoginReqDto.getPassword()));
        if (userVo == null) {
            throw new CustomException("유저네임이나 비밀번호가 틀렸습니다.");
        }
        return userVo;
    }

    @Transactional(readOnly = true)
    public EmployeeUpdateRespDto getEmployeeUpdateRespDto(Integer principalId) {
        User user = userRepository.findById(principalId);
        Employee employee = employeeRepository.findByUserId(principalId);
        EmployeeUpdateRespDto employeeUpdateRespDto = new EmployeeUpdateRespDto(user.getPassword(), user.getEmail(),
                user.getAddress(), user.getDetailAddress(), user.getTel(), employee.getRealName(),
                employee.getCareer(), employee.getEducation());
        return employeeUpdateRespDto;
    }

    @Transactional
    public void insertEmployee(EmployeeJoinReqDto employeeJoinReqDto) {
        if (userRepository.findByUsername(employeeJoinReqDto.getUsername()) != null) {
            throw new CustomException("이미 존재하는 유저네임 입니다.");
        }
        User user = new User(employeeJoinReqDto.getUsername(), employeeJoinReqDto.getPassword(),
                employeeJoinReqDto.getEmail());
        try {
            userRepository.insertForEmployee(user);
            employeeRepository.insert(user.getId());
        } catch (Exception e) {
            throw new CustomException("서버 오류: 회원 가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public UserVo updateEmpolyee(EmployeeUpdateReqDto employeeUpdateReqDto, Integer principalId,
            MultipartFile profile) {
        // String uuidImageName = PathUtil.writeImageFile(profile);

        User user = new User(principalId, employeeUpdateReqDto.getPassword(), employeeUpdateReqDto.getEmail(),
                employeeUpdateReqDto.getAddress(), employeeUpdateReqDto.getDetailAddress(),
                employeeUpdateReqDto.getTel(),
                null);
        Employee employee = new Employee(principalId, employeeUpdateReqDto.getRealName(),
                employeeUpdateReqDto.getCareer(),
                employeeUpdateReqDto.getEducation());
        try {
            userRepository.updateById(user);
            employeeRepository.updateByUserId(employee);
        } catch (Exception e) {
            throw new CustomException("회원 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        user = userRepository.findById(principalId);
        UserVo userVoPS = new UserVo(user.getId(), user.getUsername(), user.getProfile(), user.getRole());
        return userVoPS;
    }
}
