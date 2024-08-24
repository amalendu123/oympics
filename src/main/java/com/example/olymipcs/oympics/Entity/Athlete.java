package com.example.olymipcs.oympics.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Athlete {

    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    private Event event;
}
