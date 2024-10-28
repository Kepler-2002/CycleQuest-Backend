package com.cyclequest.server.config;

import com.cyclequest.server.constant.AchievementType;
import com.cyclequest.server.entity.Achievement;
import com.cyclequest.server.repository.AchievementRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.Repository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AchievementConfig {
    public static final Map<Long, AchievementType> ACHIEVEMENT_TYPE_MAP = Map.ofEntries(
        Map.entry(1001L, AchievementType.TOTAL_DISTANCE),    // 骑行100公里
        Map.entry(1002L, AchievementType.TOTAL_DISTANCE),    // 骑行500公里
        Map.entry(1003L, AchievementType.TOTAL_DISTANCE),    // 骑行1000公里
        
        Map.entry(2001L, AchievementType.TOTAL_TIME),        // 累计骑行10小时
        Map.entry(2002L, AchievementType.TOTAL_TIME),        // 累计骑行50小时
        Map.entry(2003L, AchievementType.TOTAL_TIME),        // 累计骑行100小时
        
        Map.entry(3001L, AchievementType.REGION_EXPLORER),   // 探索3个新区域
        Map.entry(3002L, AchievementType.REGION_EXPLORER),   // 探索10个新区域
        Map.entry(3003L, AchievementType.REGION_EXPLORER),   // 探索所有区域
        
        Map.entry(4001L, AchievementType.SINGLE_RIDE_DISTANCE),  // 单次骑行10公里
        Map.entry(4002L, AchievementType.SINGLE_RIDE_DISTANCE),  // 单次骑行30公里
        Map.entry(4003L, AchievementType.SINGLE_RIDE_DISTANCE)   // 单次骑行50公里
    );

    @Bean
    public Map<Long, Achievement> achievementMap(AchievementRepository achievementRepository) {
        return achievementRepository.findAll().stream()
            .collect(Collectors.toMap(
                Achievement::getAchievementId,
                achievement -> achievement
            ));
    }
}
