package shop.mtcoding.jobara.resume.dto;

import lombok.Getter;
import lombok.Setter;

public class ResumeReq {

    @Getter
    @Setter
    public static class ResumeSaveReq {
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public static class ResumeUpdateReq {
        private Integer id;
        private String title;
        private String content;
    }
}
