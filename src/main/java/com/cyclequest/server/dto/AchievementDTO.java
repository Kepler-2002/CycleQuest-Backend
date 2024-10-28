package com.cyclequest.server.dto;

import com.cyclequest.server.constant.AchievementType;
import lombok.Data;

@Data
public class AchievementDTO {
    private Long id;
    private String name;
    private String description;
    private AchievementType type;
    private Double requirement;
    private String iconUrl;
    
    @Data
    public static class UserAchievementInfo {
        private AchievementDTO achievement;
        private Double progress;
        private Long unlockedAt;
        private Boolean isDisplayed;
    }
}
