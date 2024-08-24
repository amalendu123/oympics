package com.example.olymipcs.oympics.Dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table(name = "Registration")
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private AthleteDto athlete;
    private EventDto event;
}
