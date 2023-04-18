package com.b4after.bntquestion.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
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
    @Getter
    @RequiredArgsConstructor
    private enum AgeAverageScore {
        AGED_GROUP1(12.37, 55, 64),
        AGED_GROUP2(11.17, 65, 74),
        AGED_GROUP3(10.50, 75, 84),
        AGED_GROUP4(6.64, 85, Integer.MAX_VALUE);
        private final double average;
        private final int minAge;
        private final int maxAge;

        public static double getAverageScore(int age) {
            AgeAverageScore ageAverage = Arrays.stream(AgeAverageScore.values())
                    .filter(range -> age >= range.minAge && age <= range.maxAge)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("범위 밖입니다."));
            return ageAverage.getAverage();
            }
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

