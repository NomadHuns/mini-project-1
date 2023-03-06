package shop.mtcoding.jobara.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.jobara.employee.dto.EmployeeReq.EmployeeUpdateReqDto;
import shop.mtcoding.jobara.user.vo.UserVo;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EmployeeControllerTest {

      @Autowired
      private MockMvc mvc;

      @Mock
      private EmployeeUpdateReqDto employeeUpdateReqDto;

      private MockHttpSession mockSession;

      @BeforeEach
      public void setUp() {
            UserVo principal = new UserVo();
            principal.setId(1);
            principal.setUsername("ssar");
            principal.setRole("employee");
            principal.setProfile(null);
            mockSession = new MockHttpSession();
            mockSession.setAttribute("principal", principal);
      }

      @Test
      public void join_test() throws Exception {
            // given
            String requestBody = "username=asdf&password=1234&email=asdf@nate.com";
            // when
            ResultActions resultActions = mvc.perform(post("/employee/join").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            // then
            resultActions.andExpect(status().is3xxRedirection());
      }

      @Test
      public void update_test() throws Exception {
            // given
            int id = 1;
            String requestBody = "password=1234&email=ssar@nate.com&realName=김살&address=부산시&detailAddress=12구&tel=01099876554&career=2&education=고졸";
            String requestBody2 = "password=1234&email=&tel=01099876554&career=2"; // null 테스트
            MockMultipartFile file = new MockMultipartFile(
                        "file", // 파라미터 이름
                        "filename.txt", // 파일 이름
                        "text/plain", // 파일 타입
                        "Test data".getBytes() // 파일 내용
            );
            // when
            ResultActions resultActions = mvc.perform(post("/employee/update/"+id).content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).session(mockSession));
            ResultActions resultActions2 = mvc.perform(post("/employee/update/"
                        + id).content(requestBody2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).session(mockSession));

            // then
            resultActions.andExpect(status().is3xxRedirection());
            resultActions2.andExpect(status().is4xxClientError());

      }
}
