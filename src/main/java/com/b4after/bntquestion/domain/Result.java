package com.b4after.bntquestion.domain;

import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;



@Getter
public class Result {

    private final double averageScore;
    private final int totalScore;
    private final List<GradedResult> results;

    public Result(Member member, List<Answer> answers) {
        this.averageScore = AgeAverageScore.getAverageScore(member.getAge());
        this.totalScore = calculateScore(answers);
        this.results = makeResults(answers);
    }
    private List<GradedResult> makeResults(List<Answer> answers) {
        return answers.stream()
                .map(answer -> new GradedResult(
                        answer.getQuestion().getWord(),
                        getOX(answer))
                )
                .collect(Collectors.toList());
    }

    private int calculateScore(List<Answer> answers) {
        return (int) answers.stream()
                .filter(Answer::isCorrect)
                .count();
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

