package com.example.olymipcs.oympics.Repository;

import com.example.olymipcs.oympics.Entity.MedalTally;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedalTallyRepository extends JpaRepository<MedalTally, UUID> {
    Optional<MedalTally> findByCountryCode(String countryCode);
}
