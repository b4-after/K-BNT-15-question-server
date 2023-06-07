package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompleteRateService {

    private static final Long FIRST_QUESTION_ID = 1L;
    private static final Long LAST_QUESTION_ID = 15L;

    private static final Set<Long> ALL_QUESTION_IDS = LongStream.range(FIRST_QUESTION_ID, LAST_QUESTION_ID + 1)
            .boxed()
            .collect(toSet());

    private final AnswerRepository answerRepository;

    public double calculateCompleteRate() {
        Map<Long, Set<Long>> questionIdsGroupedByMemberId = groupQuestionIdsByMemberId(answerRepository.findAll());
        int memberCount = questionIdsGroupedByMemberId.keySet().size();
        int completedCount = getCompletedCount(questionIdsGroupedByMemberId);
        return ((double) completedCount / memberCount) * 100.0;
    }

    private HashMap<Long, Set<Long>> groupQuestionIdsByMemberId(List<Answer> answers) {
        return answers.stream()
                .collect(groupingBy(
                        Answer::getMemberId,
                        HashMap::new,
                        mapping(Answer::getQuestionId, toSet()))
                );
    }

    private static int getCompletedCount(Map<Long, Set<Long>> questionIdsGroupedByMemberId) {
        AtomicInteger atomicCount = new AtomicInteger();
        questionIdsGroupedByMemberId.values()
                .forEach(questions -> increaseCountIfComplete(atomicCount, questions));
        return atomicCount.get();
    }

    private static void increaseCountIfComplete(AtomicInteger completedCount, Set<Long> questions) {
        if (questions.containsAll(ALL_QUESTION_IDS)) {
            completedCount.getAndIncrement();
        }
    }
}