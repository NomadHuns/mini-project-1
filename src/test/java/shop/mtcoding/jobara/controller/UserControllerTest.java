package shop.mtcoding.jobara.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.jobara.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

      @Autowired
      private MockMvc mvc;

      @Test
      public void login_test() throws Exception {
            // given
            String requestBody = "username=ssar&password=1234";
            // when
            ResultActions resultActions = mvc.perform(post("/user/login").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
            HttpSession session = resultActions.andReturn().getRequest().getSession();
            User usPrincipal = (User) session.getAttribute("usPrincipal");

            // then
            assertThat(usPrincipal.getUsername()).isEqualTo("ssar");
            resultActions.andExpect(status().is3xxRedirection());
      }

      @Test
      public void join_test() throws Exception {
            // given
            String requestBody = "username=love&password=1234&email=love@nate.com";
            // when
            ResultActions resultActions = mvc.perform(post("/user/join").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            // then
            resultActions.andExpect(status().is3xxRedirection());
      }
}
