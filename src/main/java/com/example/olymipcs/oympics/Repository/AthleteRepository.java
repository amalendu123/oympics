package com.example.olymipcs.oympics.Repository;

import com.example.olymipcs.oympics.Entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AthleteRepository extends  JpaRepository<Athlete, UUID> {
}
