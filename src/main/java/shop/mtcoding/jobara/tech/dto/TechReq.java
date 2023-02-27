package shop.mtcoding.jobara.tech.dto;

import lombok.Getter;
import lombok.Setter;

public class TechReq {

      @Getter
      @Setter
      public static class UserTechReqDto {
            int userId;
            private String java;
            private String cLang;
            private String python;
            private String php;
            private String jsc;
            private String ruby;
            private String assemblyLang;
            private String sqlLang;
      }

      @Getter
      @Setter
      public static class BoardTechReqDto {
            int userId;
            private String java;
            private String cLang;
            private String python;
            private String php;
            private String jsc;
            private String ruby;
            private String assemblyLang;
            private String sqlLang;
      }
}
