package com.cyclequest.server.constant;

public enum AchievementType {
    TOTAL_DISTANCE("总里程"),
    TOTAL_TIME("总时长"),
    REGION_EXPLORER("区域探索"),
    SINGLE_RIDE_DISTANCE("单次骑行距离");

    private final String description;

    AchievementType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
