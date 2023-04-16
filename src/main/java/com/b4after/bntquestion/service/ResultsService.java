package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.AnswerStatus;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Results;
import com.b4after.bntquestion.repository.AnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.b4after.bntquestion.domain.Results.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultsService {

    private final AnswerRepository answerRepository;

    private final EntityManager em;

    public Results findResults(Long memberId) {
        List<Answer> answers = answerRepository.findByMemberId(memberId);
        Member member = em.find(Member.class, memberId);
        double averageScore = getAverage(member.getAge());
        int totalScore = calculateScore(answers);

        List<MakeResults> resultsList = new ArrayList<>();

        for (Answer answer : answers) {
            String word = answer.getQuestion().getWord();
            char isCorrect = getOX(answer);
            MakeResults madeResult = new MakeResults(word, isCorrect);
            resultsList.add(madeResult);
        }

        return new Results(averageScore, totalScore, resultsList);
    }

    public double getAverage(int age) {
        double average = 0.0;
        if ((age >= 55) && (age < 65)) {
            average = 12.37;
        } else if ((age >= 65) && (age < 75)) {
            average = 11.17;
        } else if ((age >= 75) && (age < 85)) {
            average = 10.50;
        } else if (age >= 85) {
            average = 6.64;
        }
        return average;
    }

    public int calculateScore(List<Answer> answers) {
        int score = 0;

        for (Answer answer : answers) {
            if (answer.getAnswerStatus() == AnswerStatus.CORRECT) {
                score += 1;
            }
        }
        return score;
    }

    public char getOX(Answer answer) {
        char outPutAnswer;
        if (answer.getAnswerStatus() == AnswerStatus.CORRECT) {
            outPutAnswer = 'O';
        } else if (answer.getAnswerStatus() == AnswerStatus.INCORRECT) {
            outPutAnswer = 'X';
        } else {
            outPutAnswer = '?';
        }
        return outPutAnswer;
    }
}




