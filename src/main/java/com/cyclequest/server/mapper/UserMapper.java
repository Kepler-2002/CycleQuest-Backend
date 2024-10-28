package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.UserDTO;
import com.cyclequest.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AchievementMapper.class})
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "avatarUrl", source = "avatarUrl")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "totalRides", source = "totalRides")
    @Mapping(target = "totalDistance", source = "totalDistance")
    @Mapping(target = "totalRideTime", source = "totalRideTime")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "achievements", source = "achievements")
    @Mapping(target = "displayedAchievements", expression = "java(user.getDisplayedAchievements().stream().map(da -> achievementMapper.toDTO(da.getAchievement())).collect(java.util.stream.Collectors.toList()))")
    UserDTO.UserInfo toUserInfo(User user);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    UserDTO.RegisterResponse toRegisterResponse(User user);
}
