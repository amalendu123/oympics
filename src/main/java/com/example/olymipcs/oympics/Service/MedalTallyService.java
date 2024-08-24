package com.example.olymipcs.oympics.Service;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.MedalTally;
import com.example.olymipcs.oympics.Entity.ScoreEntity;
import com.example.olymipcs.oympics.Repository.MedalTallyRepository;
import com.example.olymipcs.oympics.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class MedalTallyService {

    @Autowired
    private MedalTallyRepository medalTallyRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public void assignMedalsForEvent(UUID eventId) {

        List<ScoreEntity> scores = scoreRepository.findByEventId(eventId);

        scores.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));

        if (scores.size() < 3) {
            throw new RuntimeException("Not enough participants for assigning medals");
        }

        assignMedal(scores.get(0).getAthlete(), "gold");
        assignMedal(scores.get(1).getAthlete(), "silver");
        assignMedal(scores.get(2).getAthlete(), "bronze");
    }

    private void assignMedal(Athlete athlete, String medalType) {
        String countryCode = athlete.getCountryCode();
        MedalTally medalTally = medalTallyRepository.findByCountryCode(countryCode)
                .orElse(new MedalTally(UUID.randomUUID(), countryCode, 0, 0, 0));

        switch (medalType.toLowerCase()) {
            case "gold":
                medalTally.setGoldMedals(medalTally.getGoldMedals() + 1);
                break;
            case "silver":
                medalTally.setSilverMedals(medalTally.getSilverMedals() + 1);
                break;
            case "bronze":
                medalTally.setBronzeMedals(medalTally.getBronzeMedals() + 1);
                break;
        }

        medalTallyRepository.save(medalTally);
    }
}


