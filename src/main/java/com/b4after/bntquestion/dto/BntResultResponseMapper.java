package com.b4after.bntquestion.dto;

import com.b4after.bntquestion.domain.AgeAverageScore;
import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BntResultResponseMapper {

    public static BntResultResponse toDto(Member member, List<Question> questions, List<Answer> answers) {
        double averageScore = AgeAverageScore.getAverageScore(member.getAge());
        int totalScore = calculateScore(answers);
        List<BntResultResponse.GradedResult> results = makeResults(answers, questions);
        return new BntResultResponse(averageScore, totalScore, results);
    }

    private static int calculateScore(List<Answer> answers) {
        return (int) answers.stream()
                .filter(Answer::isCorrect)
                .count();
    }

    private static List<BntResultResponse.GradedResult> makeResults(List<Answer> answers, List<Question> questions) {
        return answers.stream()
                .map(answer -> new BntResultResponse.GradedResult(
                        findQuestionWord(questions, answer.getQuestionId()),
                        getOX(answer))
                )
                .collect(Collectors.toList());
    }

    private static String findQuestionWord(List<Question> questions, Long questionId) {
        return questions.stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("답변에 해당하는 문제를 찾을 수 없습니다."))
                .getWord();
    }

    private static char getOX(Answer answer) {
        if (!answer.isGraded()) {
            return '?';
        }
        if (answer.isCorrect()) {
            return 'O';
        }
        return 'X';
    }
}
