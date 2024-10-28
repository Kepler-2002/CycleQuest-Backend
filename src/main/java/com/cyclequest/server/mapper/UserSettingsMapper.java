package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.UserSettingsDTO;
import com.cyclequest.server.entity.UserSettings;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSettingsMapper {
    UserSettingsDTO toDTO(UserSettings settings);
    
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateSettingsFromDTO(UserSettingsDTO.UpdateRequest request, @MappingTarget UserSettings settings);
}
