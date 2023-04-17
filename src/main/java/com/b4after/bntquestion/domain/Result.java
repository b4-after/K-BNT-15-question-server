package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    private double averageScore;
    private int totalScore;
    private List<MakeResult> results;

    public Result(Member member, List<Answer> answers) {
        this.averageScore = getAverage(member.getAge());
        this.totalScore = calculateScore(answers);
        this.results = makeResults(answers);
    }
    private List<MakeResult> makeResults(List<Answer> answers) {
        List<MakeResult> resultsList = new ArrayList<>();

        for (Answer answer : answers) {
            String word = answer.getQuestion().getWord();
            char isCorrect = getOX(answer);
            resultsList.add(new MakeResult(word, isCorrect));
        }
        return resultsList;
    }

    private double getAverage(int age) {
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

    private int calculateScore(List<Answer> answers) {
        int score = 0;

        for (Answer answer : answers) {
            if (answer.getAnswerStatus() == AnswerStatus.CORRECT) {
                score += 1;
            }
        }
        return score;
    }

    private char getOX(Answer answer) {
        char outPutAnswer = ' ';

        if (isCorrect(answer) == true) {
            outPutAnswer = 'O';
        } else if (isCorrect(answer) == false) {
            outPutAnswer = 'X';
        } else if (isCorrect(answer) == null) {
            outPutAnswer = '?';
        }
        return outPutAnswer;
    }
    private Boolean isCorrect(Answer answer) {
        if (answer.getAnswerStatus() == AnswerStatus.CORRECT) {
            return true;
        } else if (answer.getAnswerStatus() == AnswerStatus.INCORRECT) {
            return false;
        } else {
            return null;
        }
    }
    @Getter
    private static class MakeResult {
        private String word;
        private char isCorrect;

        public MakeResult(String word, char isCorrect) {
            this.word = word;
            this.isCorrect = isCorrect;
        }
    }
}

