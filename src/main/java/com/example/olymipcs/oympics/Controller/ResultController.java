package com.example.olymipcs.oympics.Controller;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.MedalTally;
import com.example.olymipcs.oympics.Service.RequiredService;
import com.example.olymipcs.oympics.dto.MedalSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private RequiredService requiredService;

    @GetMapping("/medalAnalysis")
    public MedalSummaryDTO getMedalAnalysis() {
        return requiredService.getMedalSummary();
    }
    @GetMapping("/topAthlete")
    public Athlete getTopAthlete() {
        return requiredService.getAthleteWithHighestMedals();
    }
    @GetMapping("/topMaleAthlete")
    public Athlete getTopMaleAthlete() {
        return requiredService.getMaleAthleteWithHighestScore();
    }

    @GetMapping("/topFemaleAthlete")
    public Athlete getTopFemaleAthlete() {
        return requiredService.getFemaleAthleteWithHighestScore();
    }
    @GetMapping("/topNMedalTallies/{n}")
    public List<MedalTally> getMedalTallyForFirstNNations(@PathVariable int n) {
        return requiredService.getMedalTallyForFirstNNations(n);
    }

    @GetMapping("/topNMedalTallies/{eventId}/{n}")
    public List<MedalTally> getMedalTallyForFirstNNations(@PathVariable UUID eventId, @PathVariable int n) {
        return requiredService.getMedalTallyForFirstNNations(eventId, n);
    }
}
