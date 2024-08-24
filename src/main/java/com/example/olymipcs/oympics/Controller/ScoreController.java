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
    @PostMapping("/simulate-event")
    public List<ScoreEntity> simulateEvent(@RequestBody SimulateEventRequest request) {
        UUID eventId = request.getEventId();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Hibernate.initialize(event.getParticipants());
        List<ScoreEntity> newScores = new ArrayList<>();
        System.out.println(event.getParticipants());
        for (Athlete athlete : event.getParticipants()) {
            ScoreEntity scoreEntity = new ScoreEntity();

            scoreEntity.setAthlete(athlete);

            scoreEntity.setEvent(event);

            scoreEntity.setScore(Math.random() * 100);

            scoreRepository.save(scoreEntity);
        }
        return newScores;
    }

}
