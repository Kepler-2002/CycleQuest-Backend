package com.cyclequest.server.mapper;

import com.cyclequest.server.dto.UserSettingsDTO;
import com.cyclequest.server.entity.UserSettings;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserSettingsMapper {
    UserSettingsDTO toDTO(UserSettings settings);
    
    void updateSettingsFromDTO(UserSettingsDTO.UpdateRequest request, @MappingTarget UserSettings settings);
}
