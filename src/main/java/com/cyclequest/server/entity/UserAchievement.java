package com.cyclequest.server.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_achievements", indexes = {
    @Index(name = "idx_user_achievement", columnList = "user_id,achievement_id", unique = true)
})
@Data
public class UserAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;

    private Long unlockedAt;
    private Double progress = 0.0;
}
