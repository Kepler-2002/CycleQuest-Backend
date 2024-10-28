package com.cyclequest.server.service;

import com.cyclequest.server.constant.AchievementType;
import com.cyclequest.server.constant.ResultCode;
import com.cyclequest.server.entity.Achievement;
import com.cyclequest.server.entity.User;
import com.cyclequest.server.entity.UserAchievement;
import com.cyclequest.server.repository.UserAchievementRepository;
import com.cyclequest.server.repository.UserRepository;
import com.cyclequest.server.repository.AchievementRepository;
import com.cyclequest.server.exception.BusinessException;
import com.cyclequest.server.mapper.AchievementMapper;
import com.cyclequest.server.dto.AchievementDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementService {

    private final UserAchievementRepository userAchievementRepository;
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;
    private final Map<Long, Achievement> achievementMap;

    public List<AchievementDTO.UserAchievementInfo> getUserAchievements(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        return userAchievementRepository.findByUserId(userId).stream()
                .map(achievementMapper::toUserAchievementInfo)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAchievementProgress(Long userId, Long achievementId, Double progress) {
        if (progress < 0 || progress > 100) {
            throw new BusinessException(ResultCode.INVALID_PROGRESS_VALUE);
        }

        UserAchievement userAchievement = userAchievementRepository
                .findByUserIdAndAchievementId(userId, achievementId)
                .orElseGet(() -> createUserAchievement(userId, achievementId));

        userAchievement.setProgress(progress);
        if (progress >= userAchievement.getAchievement().getRequirement() 
                && userAchievement.getUnlockedAt() == null) {
            userAchievement.setUnlockedAt(System.currentTimeMillis());
            log.info("用户 {} 解锁了成就 {}", userId, achievementId);
        }
        userAchievementRepository.save(userAchievement);
    }

    private UserAchievement createUserAchievement(Long userId, Long achievementId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new BusinessException(ResultCode.ACHIEVEMENT_NOT_FOUND));

        UserAchievement userAchievement = new UserAchievement();
        userAchievement.setUser(user);
        userAchievement.setAchievement(achievement);
        return userAchievement;
    }

    public AchievementType getAchievementType(Long achievementId) {
        return achievementMap.get(achievementId).getType();
    }

    public Achievement getAchievement(Long achievementId) {
        return achievementMap.get(achievementId);
    }
}
