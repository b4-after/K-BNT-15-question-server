package com.b4after.bntquestion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/livez")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/readyz")
    public ResponseEntity<String> readyCheck() {
        return ResponseEntity.ok("Success");
    }
}
