//package com.b4after.bntquestion.dto;
//
//
//import com.b4after.bntquestion.domain.Answer;
//import com.b4after.bntquestion.domain.AnswerStatus;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//public class AnswerResponseDto {
//    private List<Answer> answer;
//    private int score;
//
//    public int calculateScore(List<Answer> answers) {
//        for (Answer answer : answers) {
//            if (answer.getAnswerStatus() == AnswerStatus.CORRECT) {
//                score += 1;
//            }
//        }
//        return score;
//    }
//
//    public AnswerResponseDto(List<Answer> answer) {
//        this.answer = answer;
//        this.score = calculateScore(answer);
//    }
//
//}
