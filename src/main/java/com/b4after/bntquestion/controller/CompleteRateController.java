package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.service.CompleteRateService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompleteRateController {

    private final CompleteRateService completeRateService;
    @GetMapping("/answers/rate")
    public ResponseEntity<RateResponse> getRate(){
        RateResponse response = new RateResponse(completeRateService.calculateCompleteRate());
        return ResponseEntity.ok().body(response);
    }

    @Data
    @AllArgsConstructor
    static private class RateResponse {
        private double completeRate;
    }
}
