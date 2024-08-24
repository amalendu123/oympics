package com.example.olymipcs.oympics.Repository;

import com.example.olymipcs.oympics.Entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScoreRepository extends JpaRepository<ScoreEntity, UUID> {
    List<ScoreEntity> findByEventId(UUID eventId);
}
