package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Question;
import com.b4after.bntquestion.repository.AnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public void createAnswer(Question question, Member member, String audio) {
        Answer answer = new Answer(question, member, audio);
        answerRepository.save(answer);
    }
}
