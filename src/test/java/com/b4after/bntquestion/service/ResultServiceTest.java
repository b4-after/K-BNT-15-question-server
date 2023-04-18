package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
class ResultServiceTest {

    @Autowired
    ResultService resultService;

    @Autowired
    private EntityManager em;

    @Test
    void getResultTest() throws JsonProcessingException {
        Member member = new Member(58);
        em.persist(member);
        Question question1 = new Question(1L, "imageUrl.com", "사탕");
        Question question2 = new Question(2L, "imageUrl.com", "오이");
        em.persist(question1);
        em.persist(question2);

        Answer answer1 = new Answer(question1, member, AnswerStatus.CORRECT, "Audio.com");
        Answer answer2 = new Answer(question1, member, AnswerStatus.INCORRECT, "Audio.com");
        em.persist(answer1);
        em.persist(answer2);

        Result result = resultService.findResult(1L);

        Assertions.assertThat(result.getTotalScore()).isEqualTo(1);
    }

}