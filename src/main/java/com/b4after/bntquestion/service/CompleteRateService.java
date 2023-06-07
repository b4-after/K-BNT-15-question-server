package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompleteRateService {

    private final AnswerRepository answerRepository;

    public double calculateCompleteRate() {
        List<Answer> answers = answerRepository.findAll();
        long memberCount = answers.stream()
                .map(Answer::getMemberId)
                .distinct()
                .count();

        long completedCount = answers.stream()
                .filter(answer -> answer.getQuestionId() == 15)
                .count();

       return ((double) completedCount / memberCount) * 100.0;
    }
}