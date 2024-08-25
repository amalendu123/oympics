package com.example.olymipcs.oympics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedalTally {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String countryCode;

    private int goldMedals;

    private int silverMedals;

    private int bronzeMedals;

    private int points;
}
