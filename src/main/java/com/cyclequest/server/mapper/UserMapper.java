package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.UserDTO;
import com.cyclequest.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {System.class}, uses = {AchievementMapper.class})
public interface UserMapper {
    @Mapping(source = "userId", target = "id")
    @Mapping(target = "createdAt", expression = "java(user.getCreatedAt())")
    @Mapping(target = "achievements", source = "achievements")
    @Mapping(target = "displayedAchievements", expression = "java(user.getDisplayedAchievements().stream().map(da -> da.getAchievement()).collect(java.util.stream.Collectors.toList()))")
    UserDTO.UserInfo toUserInfo(User user);
    
    @Mapping(target = "user", source = ".")
    UserDTO.RegisterResponse toRegisterResponse(User user);
}
