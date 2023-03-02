package shop.mtcoding.jobara.employee.dto;

import lombok.Getter;
import lombok.Setter;

public class EmployeeReq {

      @Getter
      @Setter
      public static class EmployeeJoinReqDto {
            private String username;
            private String password;
            private String email;
      }

      @Getter
      @Setter
      public static class EmployeeLoginReqDto {
            private String username;
            private String password;
      }

      @Getter
      @Setter
      public static class EmployeeUpdateReqDto {
            private String password;
            private String email;
            private String address;
            private String detailAddress;
            private String tel;
            private String realName;
            private Integer career;
            private String education;
      }
}
