package com.cyclequest.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Locale;

@Entity
@Table(name = "user_settings")
@Data
public class UserSettings {
    @Id
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isDarkMode = false;
    private Boolean isNotificationEnabled = true;
    private String language = "zh_CN";
    private Boolean showDistance = true;

    @PrePersist
    protected void onCreate() {
        if (language == null) {
            language = "zh_CN";
        }
    }
}