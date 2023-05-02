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

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(length = 8, nullable = false)
    private AnswerStatus answerStatus;

    @Embedded
    private AnswerAudio answerAudio;

    public static Answer createBeforeGrading(Long questionId, Long memberId, AnswerAudio answerAudio) {
        return new Answer(questionId, memberId, answerAudio, AnswerStatus.BEFORE);
    }

    private Answer(Long questionId, Long memberId, AnswerAudio answerAudio, AnswerStatus answerStatus) {
        this.questionId = questionId;
        this.memberId = memberId;
        this.answerStatus = answerStatus;
        this.answerAudio = answerAudio;
    }

    public Answer(Long questionId, Long memberId, String audioFileObjectKey, AnswerStatus answerStatus) {
        this(questionId, memberId, new AnswerAudio(audioFileObjectKey), answerStatus);
    }

    public boolean isGraded() {
        return answerStatus != AnswerStatus.BEFORE;
    }

    public boolean isCorrect() {
        return answerStatus == AnswerStatus.CORRECT;
    }
}

