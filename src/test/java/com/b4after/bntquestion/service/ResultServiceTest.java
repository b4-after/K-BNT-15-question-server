package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class ResultServiceTest {

    @Autowired
    private ResultService resultService;

    @Autowired
    private EntityManager em;

    @Test
    void getResultTest() throws JsonProcessingException {
        // given
        Member member = new Member(58);
        em.persist(member);
        Question question1 = new Question(1L, "imageUrl.com", "사탕");
        Question question2 = new Question(2L, "imageUrl.com", "오이");
        em.persist(question1);
        em.persist(question2);

        Answer answer1 = new Answer(1L, 1L, "Audio.com", AnswerStatus.CORRECT);
        Answer answer2 = new Answer(2L, 1L, "Audio.com", AnswerStatus.INCORRECT);
        em.persist(answer1);
        em.persist(answer2);

        // when
        Result result = resultService.findResult(1L);

        // then

        assertThat(result.getTotalScore()).isEqualTo(1);
    }

}