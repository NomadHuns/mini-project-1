package shop.mtcoding.jobara.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;
import shop.mtcoding.jobara.employee.model.EmployeeRepository;

@MybatisTest
public class EmployeeRepositoryTest {

      @Autowired
      private EmployeeRepository employeeRepository;

      @Test
      public void findAllWithResume_test() {
            // given

            // when
            List<EmployeeAndResumeRespDto> list = employeeRepository.findAllWithResume();
            // then
            assertThat(list.get(0).getTitle()).isEqualTo("이력제 제목1");
            assertThat(list.get(1).getContent()).isEqualTo("이력서 내용2");
      }

      @Test
      public void findEmployeeByIdWithResume_test() {
            // given
            int id = 3;
            // when
            EmployeeAndResumeRespDto employeeAndResumeRespDto = employeeRepository.findEmployeeByIdWithResume(id);
            // then
            assertThat(employeeAndResumeRespDto.getRealName()).isEqualTo("제갈구글");
            assertThat(employeeAndResumeRespDto.getRole()).isEqualTo("employee");
      }
}
