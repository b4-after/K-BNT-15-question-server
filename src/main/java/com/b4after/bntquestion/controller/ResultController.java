package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.dto.BntResultResponse;
import com.b4after.bntquestion.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    @GetMapping("/results/{memberId}")
    public ResponseEntity<BntResultResponse> getResultsByMemberId(@PathVariable Long memberId) {
        BntResultResponse response = resultService.findResult(memberId);
        return ResponseEntity.ok().body(response);
    }

}
