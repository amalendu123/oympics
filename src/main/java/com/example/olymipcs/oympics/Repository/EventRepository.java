package com.example.olymipcs.oympics.Repository;

import com.example.olymipcs.oympics.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
