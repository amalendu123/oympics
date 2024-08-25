package com.example.olymipcs.oympics.Service;

import jakarta.persistence.FetchType;
import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import com.example.olymipcs.oympics.Repository.AthleteRepository;
import com.example.olymipcs.oympics.Repository.EventRepository;
import com.example.olymipcs.oympics.Repository.RegisterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    final RegisterRepository registerRepository;

    @Autowired
    final EventRepository eventRepository;

    @Autowired
    final AthleteRepository athleteRepository;

    public RegisterService(RegisterRepository registerRepository, EventRepository eventRepository, AthleteRepository athleteRepository) {
        this.registerRepository = registerRepository;
        this.eventRepository = eventRepository;
        this.athleteRepository = athleteRepository;
    }


    @Transactional
    public RegistrationEntity registerAthlete(Athlete athlete, UUID eventItemId) {
        System.out.println("Executing registerAthlete method");

        Event eventItem = eventRepository.findById(eventItemId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        athlete.setEvent(eventItem);
        athleteRepository.save(athlete);

        RegistrationEntity newRegistration = RegistrationEntity.builder()
                .athlete(athlete)
                .event(eventItem)
                .build();

        if (eventItem.getParticipants() == null) {
            eventItem.setParticipants(new ArrayList<>());
        }
        eventItem.getParticipants().add(athlete);
        eventRepository.save(eventItem);

        return registerRepository.save(newRegistration);
    }

}