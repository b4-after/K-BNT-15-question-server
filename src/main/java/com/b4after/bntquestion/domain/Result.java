package com.b4after.bntquestion.domain;

import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;



@Getter
public class Result {

    private final double averageScore;
    private final int totalScore;
    private final List<GradedResult> results;

    public Result(Member member, List<Answer> answers, List<Question> questions) {
        this.averageScore = AgeAverageScore.getAverageScore(member.getAge());
        this.totalScore = calculateScore(answers);
        this.results = makeResults(answers, questions);
    }
    private int calculateScore(List<Answer> answers) {
        return (int) answers.stream()
                .filter(Answer::isCorrect)
                .count();
    }

    private List<GradedResult> makeResults(List<Answer> answers, List<Question> questions) {
        return answers.stream()
                .map(answer -> new GradedResult(
                        findQuestionWord(questions, answer.getQuestionId()),
                        getOX(answer))
                )
                .collect(Collectors.toList());
    }

    private char getOX(Answer answer) {
        if (!answer.isGraded()) {
            return '?';
        }
        if (answer.isCorrect()) {
            return 'O';
        }
        return 'X';
    }

    private String findQuestionWord(List<Question> questions, Long questionId) {
        return questions.stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("답변에 해당하는 문제를 찾을 수 없습니다."))
                .getWord();
    }
    @Getter
    private static class GradedResult {
        private final String word;
        private final char isCorrect;

        public GradedResult(String word, char isCorrect) {
            this.word = word;
            this.isCorrect = isCorrect;
        }
    }
}
