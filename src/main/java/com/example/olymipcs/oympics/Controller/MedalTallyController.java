package com.example.olymipcs.oympics.Controller;

import com.example.olymipcs.oympics.Service.MedalTallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/medal-tally")
public class MedalTallyController {

    @Autowired
    private MedalTallyService medalTallyService;

    @PostMapping("/assign-medals/{eventId}")
    public ResponseEntity<String> assignMedals(@PathVariable UUID eventId) {
        try {
            medalTallyService.assignMedalsForEvent(eventId);
            return ResponseEntity.ok("Medals assigned successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
