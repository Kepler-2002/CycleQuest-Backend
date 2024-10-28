package com.cyclequest.server.service;

import com.cyclequest.server.constant.ResultCode;
import com.cyclequest.server.dto.UserDTO;
import com.cyclequest.server.dto.UserSettingsDTO;
import com.cyclequest.server.entity.User;
import com.cyclequest.server.entity.UserSettings;
import com.cyclequest.server.mapper.UserSettingsMapper;
import com.cyclequest.server.repository.UserRepository;
import com.cyclequest.server.repository.UserSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cyclequest.server.mapper.UserMapper;
import com.cyclequest.server.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

// service/UserService.java
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSettingsRepository userSettingsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserSettingsMapper userSettingsMapper;


    @Transactional
    public UserDTO.RegisterResponse register(UserDTO.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ResultCode.EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 创建默认用户设置
        UserSettings settings = new UserSettings();
        settings.setUser(user);
        user.setSettings(settings);

        User savedUser = userRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getUsername());
        return userMapper.toRegisterResponse(savedUser);
    }

    public UserDTO.UserInfo getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        return userMapper.toUserInfo(user);
    }

    @Transactional
    public UserSettingsDTO updateUserSettings(Long userId, UserSettingsDTO.UpdateRequest request) {
        UserSettings settings = userSettingsRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        
        userSettingsMapper.updateSettingsFromDTO(request, settings);
        return userSettingsMapper.toDTO(userSettingsRepository.save(settings));
    }

    public UserSettingsDTO getUserSettings(Long userId) {
        UserSettings settings = userSettingsRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        return userSettingsMapper.toDTO(settings);
    }
}
