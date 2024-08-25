package com.example.olymipcs.oympics.Controller;


import com.example.olymipcs.oympics.Entity.Athlete;
import com.example.olymipcs.oympics.Entity.RegistrationEntity;
import com.example.olymipcs.oympics.Service.RegisterService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/register")
public class AthleteController {
    final RegisterService registerService;

    public AthleteController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/athlete")
    public RegistrationEntity registerAthlete(@RequestBody Athlete athlete, @RequestParam UUID eventItemId) {
        return registerService.registerAthlete(athlete, eventItemId);
    }
}
