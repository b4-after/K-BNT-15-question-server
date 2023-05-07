package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.dto.AnswerCreateRequest;
import com.b4after.bntquestion.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/answers")
    public ResponseEntity<Void> registerAnswer(@ModelAttribute AnswerCreateRequest request) {
        Long answerId = answerService.createAnswer(request);
        return ResponseEntity.created(URI.create("/answers/" + answerId))
                .build();
    }
}
