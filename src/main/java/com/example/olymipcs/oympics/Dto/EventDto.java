package com.example.olymipcs.oympics.Dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Table(name = "Event")
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private UUID id;
    private String name;
    private List<ScoreDto> scores;
    private List<AthleteDto> participants; // Use summary DTO for participants
}
