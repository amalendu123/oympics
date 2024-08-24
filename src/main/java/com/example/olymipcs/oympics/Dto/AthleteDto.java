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
@Table(name = "Athlete")
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto {
    private UUID id;
    private String firstName;
    private String lastName;
}
