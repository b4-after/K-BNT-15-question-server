package com.b4after.bntquestion.controller;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckContoller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/livez")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/readyz")
    public ResponseEntity<String> readyCheck() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return ResponseEntity.ok("Success");
        } catch (Exception error) {
            return ResponseEntity.status(500).body("Database Error");
        }
    }
}
