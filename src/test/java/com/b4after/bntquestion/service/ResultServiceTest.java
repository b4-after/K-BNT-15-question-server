package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.AnswerStatus;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Question;
import com.b4after.bntquestion.dto.BntResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private AnswerAudioUploader answerAudioUploader;

    @Test
    void getResultTest() {
        // given
        Member member = new Member(58);
        em.persist(member);
        Question question1 = new Question(1L, "imageUrl.com", "사탕");
        Question question2 = new Question(2L, "imageUrl.com", "오이");
        em.persist(question1);
        em.persist(question2);

        Answer answer1 = new Answer(1L, 1L, "Audio1.com", AnswerStatus.CORRECT);
        Answer answer2 = new Answer(2L, 1L, "Audio2.com", AnswerStatus.INCORRECT);
        em.persist(answer1);
        em.persist(answer2);

        // when
        BntResultResponse result = resultService.findResult(1L);

        // then

        assertThat(result.getTotalScore()).isEqualTo(1);
    }
}
