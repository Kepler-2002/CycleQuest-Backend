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
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private Integer totalRides = 0;
    private Float totalDistance = 0.0f;
    private Long totalRideTime = 0L;
    private Long createdAt = System.currentTimeMillis();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAchievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("displayOrder ASC")
    private List<UserDisplayedAchievement> displayedAchievements = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserSettings settings;

    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }

    private Long lastLoginAt;
    private Long updatedAt;
}
