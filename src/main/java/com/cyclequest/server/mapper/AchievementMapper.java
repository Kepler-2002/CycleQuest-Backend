package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.AchievementDTO;
import com.cyclequest.server.entity.Achievement;
import com.cyclequest.server.entity.UserAchievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AchievementMapper {
    @Mapping(target = "id", source = "id")
    AchievementDTO toDTO(Achievement achievement);
    
    @Mapping(target = "achievement", source = "achievement")
    @Mapping(target = "progress", source = "progress")
    @Mapping(target = "unlockedAt", source = "unlockedAt")
    @Mapping(target = "isDisplayed", expression = "java(userAchievement.getUser().getDisplayedAchievements().stream().anyMatch(da -> da.getAchievement().equals(userAchievement.getAchievement())))")
    AchievementDTO.UserAchievementInfo toUserAchievementInfo(UserAchievement userAchievement);
}
