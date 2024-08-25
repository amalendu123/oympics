package com.example.olymipcs.oympics.Controller;

import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Service.RequiredService;
import com.example.olymipcs.oympics.dto.MedalSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
