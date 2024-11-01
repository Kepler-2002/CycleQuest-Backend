package com.cyclequest.server.repository;


import com.cyclequest.server.constant.AchievementType;
import com.cyclequest.server.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByType(AchievementType type);
}
