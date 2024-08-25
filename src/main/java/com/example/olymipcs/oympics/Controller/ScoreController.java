package com.example.olymipcs.oympics.Controller;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.ScoreEntity;
import com.example.olymipcs.oympics.Entity.SimulateEventRequest;
import com.example.olymipcs.oympics.Repository.AthleteRepository;
import com.example.olymipcs.oympics.Repository.EventRepository;
import com.example.olymipcs.oympics.Repository.ScoreRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/events")
public class ScoreController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Transactional
    @PostMapping("/simulate-all-events")
    public List<ScoreEntity> simulateAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        List<ScoreEntity> newScores = new ArrayList<>();

        for (Event event : allEvents) {
            Hibernate.initialize(event.getParticipants());
            for (Athlete athlete : event.getParticipants()) {
                ScoreEntity scoreEntity = new ScoreEntity();
                scoreEntity.setAthlete(athlete);
                scoreEntity.setEvent(event);
                scoreEntity.setScore(Math.random() * 100); // Simulate a score between 0 and 100
                scoreRepository.save(scoreEntity);
                newScores.add(scoreEntity); // Collect the scores
            }
        }

        return newScores;
    }

}
