package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String word;

    public Question(Long id, String imageUrl, String word) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.word = word;
    }
}

