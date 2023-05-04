package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.dto.QuestionResponse;
import com.b4after.bntquestion.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long questionId) {
        QuestionResponse response = questionService.getQuestionImage(questionId);
        return ResponseEntity.ok().body(response);
    }

}
