package com.cyclequest.server.entity;

import com.cyclequest.server.constant.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

// entity/User.java
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_username", columnList = "username"),
    @Index(name = "idx_email", columnList = "email")
})
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private Integer totalRides = 0;
    private Float totalDistance = 0.0f;
    private Long totalRideTime = 0L;

    private Long createdAt;
    private Long lastLoginAt;
    private Long updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserSettings settings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAchievement> achievements = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("displayOrder ASC")
    private List<UserDisplayedAchievement> displayedAchievements = new ArrayList<>();
}
