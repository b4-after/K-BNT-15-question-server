package com.b4after.bntquestion.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private int age;

    private LocalDateTime createdAt;

    public Member(int age) {
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }


}

