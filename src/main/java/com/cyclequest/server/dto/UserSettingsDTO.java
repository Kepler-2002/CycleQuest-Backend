package com.cyclequest.server.dto;

import lombok.Data;

@Data
public class UserSettingsDTO {
    private Boolean isDarkMode;
    private Boolean isNotificationEnabled;
    private String language;
    private Boolean showDistance;

    @Data
    public static class UpdateRequest {
        private Boolean isDarkMode;
        private Boolean isNotificationEnabled;
        private String language;
        private Boolean showDistance;
    }
}
