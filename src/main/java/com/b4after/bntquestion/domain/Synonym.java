package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Synonym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "synonym_id")
    private Long id;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String synonymWord;
}
