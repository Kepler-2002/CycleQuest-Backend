package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.AchievementDTO;
import com.cyclequest.server.entity.Achievement;
import com.cyclequest.server.entity.UserAchievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AchievementMapper {
    AchievementDTO toDTO(Achievement achievement);
    
    @Mapping(target = "achievement", source = "achievement")
    @Mapping(target = "progress", source = "progress")
    @Mapping(target = "unlockedAt", source = "unlockedAt")
    AchievementDTO.UserAchievementInfo toUserAchievementInfo(UserAchievement userAchievement);
}
