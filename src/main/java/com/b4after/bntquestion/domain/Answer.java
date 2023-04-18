package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;

    private String audioUrl;

    public boolean isGraded() {
        return answerStatus != AnswerStatus.BEFORE;
    }
    public boolean isCorrect() {
        return answerStatus == AnswerStatus.CORRECT;
    }

    public Answer(Question question, Member member, String audioUrl) {
        this.question = question;
        this.member = member;
        this.answerStatus = AnswerStatus.BEFORE;
        this.audioUrl = audioUrl;
    }
    //테스트를 위한 생성자 오버로드
    public Answer(Question question, Member member, AnswerStatus answerStatus, String audioUrl) {
        this.question = question;
        this.member = member;
        this.answerStatus = answerStatus;
        this.audioUrl = audioUrl;
    }
}

