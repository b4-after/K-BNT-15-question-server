package com.b4after.bntquestion.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;

    @JoinColumn(name = "audio_url")
    private String audio;
    // 스태틱 생성 메소드
    public static Answer createAnswer(Question question, Member member, String audio) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setMember(member);
        answer.setAudio(audio);
        answer.setAnswerStatus(AnswerStatus.BEFORE);
        return answer;
    }

    public char getOX() {
        char outPutAnswer;
        if (this.answerStatus == AnswerStatus.CORRECT) {
            outPutAnswer = 'O';
        } else if (this.answerStatus == AnswerStatus.INCORRECT) {
            outPutAnswer = 'X';
        } else {
            outPutAnswer = '?';
        }
        return outPutAnswer;
    }

}
