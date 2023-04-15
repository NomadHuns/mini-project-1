package shop.mtcoding.jobara.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApplyReq {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplyDecideReqDto {
        private Integer userId;
        private Integer state;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplyReqDto {
        private Integer boardId;
        private Integer resumeId;
    }

}
