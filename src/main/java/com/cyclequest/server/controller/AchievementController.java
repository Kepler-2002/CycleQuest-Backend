package com.cyclequest.server.controller;

import com.cyclequest.server.model.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cyclequest.server.service.AchievementService;
import com.cyclequest.server.mapper.AchievementMapper;
import com.cyclequest.server.dto.AchievementDTO;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;
    private final AchievementMapper achievementMapper;

    @GetMapping("/users/{userId}")
    public ApiResponse<List<AchievementDTO.UserAchievementInfo>> getUserAchievements(
            @PathVariable Long userId) {
        return ApiResponse.success(achievementService.getUserAchievements(userId));
    }

    @PutMapping("/users/{userId}/{achievementId}/progress")
    public ApiResponse<Void> updateAchievementProgress(
            @PathVariable Long userId,
            @PathVariable Long achievementId,
            @RequestParam Double progress) {
        achievementService.updateAchievementProgress(userId, achievementId, progress);
        return ApiResponse.success(null);
    }
}
