package com.cyclequest.server.config;

import com.cyclequest.server.constant.AchievementType;
import com.cyclequest.server.entity.Achievement;
import com.cyclequest.server.repository.AchievementRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cyclequest.server.constant.AchievementType.*;


@Configuration
@RequiredArgsConstructor
public class AchievementConfig {
    private final AchievementRepository achievementRepository;

    private static final List<AchievementDefinition> ACHIEVEMENT_DEFINITIONS = Arrays.asList(
        // 总里程成就
        new AchievementDefinition(1L, "初级骑手", "累计骑行达到100公里", TOTAL_DISTANCE, 100.0),
        new AchievementDefinition(2L, "中级骑手", "累计骑行达到1000公里", TOTAL_DISTANCE, 1000.0),
        new AchievementDefinition(3L, "高级骑手", "累计骑行达到5000公里", TOTAL_DISTANCE, 5000.0),
        
        // 单次骑行成就
        new AchievementDefinition(4L, "短途专家", "单次骑行超过30公里", SINGLE_RIDE_DISTANCE, 30.0),
        new AchievementDefinition(5L, "长途专家", "单次骑行超过50公里", SINGLE_RIDE_DISTANCE, 50.0),
        
        // 骑行时长成就
        new AchievementDefinition(6L, "耐力新手", "累计骑行时间达到24小时", TOTAL_TIME, 24.0),
        new AchievementDefinition(7L, "耐力达人", "累计骑行时间达到100小时", TOTAL_TIME, 100.0)
    );

    @Bean
    public Map<Long, Achievement> achievementMap() {
        return achievementRepository.findAll().stream()
                .collect(Collectors.toMap(Achievement::getId, a -> a));
    }

    @PostConstruct
    public void initAchievements() {
        if (achievementRepository.count() > 0) {
            return;
        }
        List<Achievement> achievements = ACHIEVEMENT_DEFINITIONS.stream()
                .map(this::createAchievement)
                .collect(Collectors.toList());
        achievementRepository.saveAll(achievements);
    }

    private Achievement createAchievement(AchievementDefinition def) {
        Achievement achievement = new Achievement();
        achievement.setId(def.id());
        achievement.setName(def.name());
        achievement.setDescription(def.description());
        achievement.setType(def.type());
        achievement.setRequirement(def.requirement());
        achievement.setIconUrl(String.format("/images/achievements/%s-%s.png", 
            def.type().name().toLowerCase(), def.requirement().intValue()));
        return achievement;
    }

    private record AchievementDefinition(
        Long id,
        String name,
        String description,
        AchievementType type,
        Double requirement
    ) {}
}
