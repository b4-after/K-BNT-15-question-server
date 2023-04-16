package com.b4after.bntquestion.controller;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.dto.AnswerResponseDto;
import com.b4after.bntquestion.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ResultController {
    private final AnswerService answerService;
    @GetMapping("/result/{memberId}")
    public ResponseEntity<AnswerResponseDto> getResultsByMemberId(@PathVariable Long memberId) {
        List<Answer> answers = answerService.findResult(memberId);
        AnswerResponseDto response = new AnswerResponseDto(answers);
        return ResponseEntity.ok().body(response);
    }

}
