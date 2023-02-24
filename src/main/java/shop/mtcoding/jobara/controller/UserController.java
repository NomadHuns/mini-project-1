package shop.mtcoding.jobara.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.service.UserService;
import shop.mtcoding.jobara.util.Verify;

@Controller
public class UserController {

      @Autowired
      private UserService userService;

      @Autowired
      private HttpSession session;

      @GetMapping("/user/{id}")
      public String detail(@PathVariable int id) {
            return "user/detail";
      }

      @GetMapping("/user/joinForm")
      public String joinForm() {
            return "user/joinForm";
      }

      @PostMapping("/user/join")
      public String login(UserJoinReqDto userJoinReqDto) {
            Verify.validateStiring(userJoinReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(userJoinReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(userJoinReqDto.getEmail(), "암호를 입력하세요.");
            userService.join(userJoinReqDto);
            return "redirect:/";
      }

      @GetMapping("/user/list")
      public String list() {
            return "user/list";
      }

      @GetMapping("/user/loginForm")
      public String loginForm() {
            return "user/loginForm";
      }

      @PostMapping("/user/login")
      public String login(UserLoginReqDto userLoginReqDto) {
            Verify.validateStiring(userLoginReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(userLoginReqDto.getPassword(), "암호를 입력하세요.");
            User userPS = userService.login(userLoginReqDto);
            session.setAttribute("usPrincipal", userPS);
            return "redirect:/";
      }

      @GetMapping("/user/updateForm")
      public String updateForm() {
            return "user/updateForm";
      }

}
