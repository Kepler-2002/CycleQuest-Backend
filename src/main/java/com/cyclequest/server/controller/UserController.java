// controller/UserController.java
package com.cyclequest.server.controller;

import com.cyclequest.server.dto.UserDTO;
import com.cyclequest.server.dto.UserSettingsDTO;
import com.cyclequest.server.model.response.ApiResponse;
import com.cyclequest.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserDTO.RegisterResponse> register(
            @Validated(UserDTO.Create.class) @RequestBody UserDTO.RegisterRequest request) {
        return ApiResponse.success(userService.register(request));
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDTO.UserInfo> getUserById(@PathVariable Long userId) {
        return ApiResponse.success(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/settings")
    public ApiResponse<UserSettingsDTO> getUserSettings(@PathVariable Long userId) {
        return ApiResponse.success(userService.getUserSettings(userId));
    }

    @PutMapping("/{userId}/settings")
    public ApiResponse<UserSettingsDTO> updateUserSettings(
            @PathVariable Long userId,
            @Validated @RequestBody UserSettingsDTO.UpdateRequest request) {
        return ApiResponse.success(userService.updateUserSettings(userId, request));
    }
}
