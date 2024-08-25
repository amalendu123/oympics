package com.example.olymipcs.oympics.Service;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.Event;
import com.example.olymipcs.oympics.Entity.MedalTally;
import com.example.olymipcs.oympics.Entity.ScoreEntity;
import com.example.olymipcs.oympics.Repository.EventRepository;
import com.example.olymipcs.oympics.Repository.MedalTallyRepository;
import com.example.olymipcs.oympics.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedalTallyService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private MedalTallyRepository medalTallyRepository;

    @Transactional
    public void assignMedalsForAllEvents() {
        // Fetch all events
        List<Event> events = eventRepository.findAll();

        // Iterate through each event and assign medals
        for (Event event : events) {
            assignMedalsForEvent(event.getId());
        }
    }

    public void assignMedalsForEvent(UUID eventId) {
        List<ScoreEntity> scores = scoreRepository.findByEventId(eventId);

        scores.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));

        if (scores.size() < 3) {
            throw new RuntimeException("Not enough participants for assigning medals");
        }

        // Assign medals
        assignMedal(scores.get(0).getAthlete(), "gold");
        assignMedal(scores.get(1).getAthlete(), "silver");
        assignMedal(scores.get(2).getAthlete(), "bronze");
    }

    private void assignMedal(Athlete athlete, String medalType) {
        String countryCode = athlete.getCountryCode();
        MedalTally medalTally = medalTallyRepository.findByCountryCode(countryCode)
                .orElse(new MedalTally(UUID.randomUUID(), countryCode, 0, 0, 0, 0));

        switch (medalType.toLowerCase()) {
            case "gold":
                medalTally.setGoldMedals(medalTally.getGoldMedals() + 1);
                medalTally.setPoints(medalTally.getPoints() + 10); // Add 10 points for gold
                break;
            case "silver":
                medalTally.setSilverMedals(medalTally.getSilverMedals() + 1);
                medalTally.setPoints(medalTally.getPoints() + 5);  // Add 5 points for silver
                break;
            case "bronze":
                medalTally.setBronzeMedals(medalTally.getBronzeMedals() + 1);
                medalTally.setPoints(medalTally.getPoints() + 2);  // Add 2 points for bronze
                break;
        }

        medalTallyRepository.save(medalTally);
    }


}
