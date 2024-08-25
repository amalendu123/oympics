package com.example.olymipcs.oympics.Controller;

import com.example.olymipcs.oympics.Service.MedalTallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medal-tally")
public class MedalTallyController {

    @Autowired
    private MedalTallyService medalTallyService;

    @PostMapping("/assign-medals")
    public ResponseEntity<String> assignMedals() {
        try {
            medalTallyService.assignMedalsForAllEvents();
            return ResponseEntity.ok("Medals assigned successfully to all events.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
