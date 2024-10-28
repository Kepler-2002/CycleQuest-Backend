package com.cyclequest.server.entity;

import com.cyclequest.server.constant.AchievementType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "achievements")
@Data
public class Achievement {
    @Id
    private Long achievementId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private AchievementType type;
    
    private Double requirement;
    
    private String iconUrl;
}
