package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Results {

    private double averageScore;
    private int totalScore;
    private List<MakeResults> results;

    public Results(double averageScore, int totalScore, List<MakeResults> results) {
        this.averageScore = averageScore;
        this.totalScore = totalScore;
        this.results = results;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MakeResults {
        private String word;
        private char isCorrect;

        public MakeResults(String word, char isCorrect) {
            this.word = word;
            this.isCorrect = isCorrect;
        }
    }
}

