package sch.cqre.api.dto;

import lombok.*;
import sch.cqre.api.domain.NotificationEntity;

public class NotiDto {

    @Data
    @Builder
    public static class NotiResponse extends NotificationEntity {
        private Long notiId;
        private boolean whether;
    }

    @Data
    @Builder
    public static class CheckNotiResponse {
        private Long notiId;
        private boolean whether;
    }


    @Setter @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteNotiResponseDto {
        private Long notiId;
    }


}