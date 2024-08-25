package com.example.olymipcs.oympics.Service;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.MedalTally;
import com.example.olymipcs.oympics.Entity.ScoreEntity;
import com.example.olymipcs.oympics.Repository.MedalTallyRepository;
import com.example.olymipcs.oympics.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequiredService {

    @Autowired
    final MedalTallyRepository medalTallyRepository;
    final ScoreRepository scoreRepository;
    public RequiredService(MedalTallyRepository medalTallyRepository, ScoreRepository scoreRepository) {
        this.medalTallyRepository = medalTallyRepository;
        this.scoreRepository = scoreRepository;
    }

    public com.example.olymipcs.oympics.dto.MedalSummaryDTO getMedalSummary() {
        List<MedalTally> medalTallies = medalTallyRepository.findAll();

        Optional<MedalTally> highestGoldCountry = medalTallies.stream()
                .max(Comparator.comparingInt(MedalTally::getGoldMedals));

        Optional<MedalTally> highestSilverCountry = medalTallies.stream()
                .max(Comparator.comparingInt(MedalTally::getSilverMedals));

        Optional<MedalTally> lowestSilverCountry = medalTallies.stream()
                .filter(m -> m.getSilverMedals() > 0)
                .min(Comparator.comparingInt(MedalTally::getSilverMedals));

        Optional<MedalTally> lowestGoldCountry = medalTallies.stream()
                .filter(m -> m.getGoldMedals() > 0)
                .min(Comparator.comparingInt(MedalTally::getGoldMedals));
        Optional<MedalTally> highestPointCountry = medalTallies.stream()
                .max(Comparator.comparingInt(MedalTally::getPoints));

        Optional<MedalTally> lowestPointCountry = medalTallies.stream()
                .min(Comparator.comparingInt(MedalTally::getPoints));

        int totalGoldMedals = medalTallies.stream().mapToInt(MedalTally::getGoldMedals).sum();
        int totalSilverMedals = medalTallies.stream().mapToInt(MedalTally::getSilverMedals).sum();
        int totalBronzeMedals = medalTallies.stream().mapToInt(MedalTally::getBronzeMedals).sum();

        return new com.example.olymipcs.oympics.dto.MedalSummaryDTO(
                highestGoldCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                highestSilverCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                lowestSilverCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                lowestGoldCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                highestPointCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                lowestPointCountry.map(MedalTally::getCountryCode).orElse("N/A"),
                totalGoldMedals,
                totalSilverMedals,
                totalBronzeMedals
        );
    }
    public Athlete getAthleteWithHighestMedals() {
        List<ScoreEntity> scores = scoreRepository.findAll();

        Map<Athlete, Long> medalCountByAthlete = scores.stream()
                .collect(Collectors.groupingBy(ScoreEntity::getAthlete, Collectors.counting()));

        return medalCountByAthlete.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    //male
    public Athlete getMaleAthleteWithHighestScore() {
        List<ScoreEntity> scores = scoreRepository.findAll();

        Map<Athlete, Double> totalScoreByMaleAthlete = scores.stream()
                .filter(score -> "male".equalsIgnoreCase(score.getAthlete().getGender()))
                .collect(Collectors.groupingBy(
                        ScoreEntity::getAthlete,
                        Collectors.summingDouble(ScoreEntity::getScore)
                ));

        return totalScoreByMaleAthlete.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    //female
    public Athlete getFemaleAthleteWithHighestScore() {
        List<ScoreEntity> scores = scoreRepository.findAll();

        Map<Athlete, Double> totalScoreByMaleAthlete = scores.stream()
                .filter(score -> "Female".equalsIgnoreCase(score.getAthlete().getGender()))
                .collect(Collectors.groupingBy(
                        ScoreEntity::getAthlete,
                        Collectors.summingDouble(ScoreEntity::getScore)
                ));

        return totalScoreByMaleAthlete.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    public List<MedalTally> getMedalTallyForFirstNNations(int n) {
        List<MedalTally> allMedalTallies = medalTallyRepository.findAll();

        return allMedalTallies.stream()
                .sorted((m1, m2) -> m1.getCountryCode().compareTo(m2.getCountryCode())) // Adjust this sorting logic if you have a participation order
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<MedalTally> getMedalTallyForFirstNNations(UUID eventId, int n) {
        List<MedalTally> medalTallies = scoreRepository.findByEventId(eventId).stream()
                .map(score -> medalTallyRepository.findByCountryCode(score.getAthlete().getCountryCode())
                        .orElse(null))
                .distinct()
                .filter(medalTally -> medalTally != null)
                .sorted((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()))
                .collect(Collectors.toList());
        return medalTallies.stream().limit(n).collect(Collectors.toList());
    }
}