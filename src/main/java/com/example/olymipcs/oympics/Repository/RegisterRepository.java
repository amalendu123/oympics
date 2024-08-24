package com.example.olymipcs.oympics.Repository;

import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegisterRepository extends JpaRepository<RegistrationEntity, UUID> {
}
