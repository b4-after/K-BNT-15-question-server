package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "idx_member", columnList = "memberId"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    private Long questionId;

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;

    private String audioUrl;

    public Answer(Long questionId, Long memberId, String audioUrl) {
        this(questionId, memberId, audioUrl, AnswerStatus.BEFORE);
    }

    public Answer(Long questionId, Long memberId, String audioUrl, AnswerStatus answerStatus) {
        this.questionId = questionId;
        this.memberId = memberId;
        this.answerStatus = answerStatus;
        this.audioUrl = audioUrl;
    }

    public boolean isGraded() {
        return answerStatus != AnswerStatus.BEFORE;
    }

    public boolean isCorrect() {
        return answerStatus == AnswerStatus.CORRECT;
    }
}

