package com.example.olymipcs.oympics.Service;

import com.example.olymipcs.oympics.Entity.MedalTally;
import com.example.olymipcs.oympics.Repository.MedalTallyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RequiredService {

    @Autowired
    final MedalTallyRepository medalTallyRepository;

    public RequiredService(MedalTallyRepository medalTallyRepository) {
        this.medalTallyRepository = medalTallyRepository;
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
}