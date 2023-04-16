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

    @JoinColumn(name = "audio_url")
    private String audio;

    public Answer(Question question, Member member, String audio) {
        this.question = question;
        this.member = member;
        this.answerStatus = AnswerStatus.BEFORE;
        this.audio = audio;
    }
}

