package com.example.olymipcs.oympics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedalSummaryDTO {
    private String highestGoldCountry;
    private String highestSilverCountry;
    private String lowestSilverCountry;
    private String lowestGoldCountry;
    private String highestPointCountry;
    private String lowestPointCountry;
    private int totalGoldMedals;
    private int totalSilverMedals;
    private int totalBronzeMedals;
}
