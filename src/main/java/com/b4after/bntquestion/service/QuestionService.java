package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Question;
import com.b4after.bntquestion.dto.QuestionResponse;
import com.b4after.bntquestion.exception.BusinessException;
import com.b4after.bntquestion.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionResponse getQuestionImage(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessException("해당하는 문제가 존재하지 않습니다."));
        QuestionResponse response = new QuestionResponse(question.getImageUrl());
        return response;
    }
}
