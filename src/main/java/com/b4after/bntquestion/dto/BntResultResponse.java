package com.b4after.bntquestion.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BntResultResponse {

    private final double averageScore;
    private final int totalScore;
    private final List<GradedResult> results;

    @Getter
    static class GradedResult {
        private final String word;
        private final char isCorrect;

        public GradedResult(String word, char isCorrect) {
            this.word = word;
            this.isCorrect = isCorrect;
        }
    }
}
