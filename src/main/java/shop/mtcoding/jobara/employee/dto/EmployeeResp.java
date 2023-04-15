package shop.mtcoding.jobara.employee.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.common.util.DateParse;

public class EmployeeResp {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeUpdateRespDto {
        private Integer id;
        private String password;
        private String email;
        private String address;
        private String detailAddress;
        private String tel;
        private String realName;
        private Integer career;
        private String education;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeAndResumeRespDto {
        private Integer id;
        private String username;
        private String password;
        private String email;
        private String address;
        private String detailAddress;
        private String tel;
        private String profile;
        private String role;
        private Timestamp createdAt;
        private String realName;
        private String education;
        private Integer career;
        private String title;
        private String content;

        public String getCreatedAtToString() {
            return DateParse.format(createdAt);
        }
    }
}
