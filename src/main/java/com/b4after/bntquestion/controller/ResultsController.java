package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.domain.Results;
import com.b4after.bntquestion.service.ResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ResultsController {
    private final ResultsService resultsService;
    @GetMapping("/results/{memberId}")
    public ResponseEntity<Results> getResultsByMemberId(@PathVariable Long memberId) {
        Results results = resultsService.findResults(memberId);
        return ResponseEntity.ok().body(results);
    }

}
