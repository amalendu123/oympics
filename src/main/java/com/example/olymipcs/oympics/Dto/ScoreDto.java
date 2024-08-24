package com.example.olymipcs.oympics.Dto;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table(name = "Score")
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {
    private UUID id;
    private UUID eventId;
    private UUID athleteId;
    private double score;
}