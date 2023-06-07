package com.b4after.bntquestion.acceptance;

import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.AnswerStatus;
import com.b4after.bntquestion.repository.AnswerRepository;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class completeRateAcceptanceTest extends AcceptanceTest{

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void executeBNT() {
        answerRepository.saveAll(List.of(
                new Answer(1L, 1L, "audio1.com", AnswerStatus.CORRECT),
                new Answer(2L, 1L, "audio2.com", AnswerStatus.CORRECT),
                new Answer(3L, 1L, "audio3.com", AnswerStatus.CORRECT),
                new Answer(4L, 1L, "audio4.com", AnswerStatus.CORRECT),
                new Answer(5L, 1L, "audio5.com", AnswerStatus.CORRECT),
                new Answer(6L, 1L, "audio6.com", AnswerStatus.CORRECT),
                new Answer(7L, 1L, "audio7.com", AnswerStatus.CORRECT),
                new Answer(8L, 1L, "audio8.com", AnswerStatus.CORRECT),
                new Answer(9L, 1L, "audio9.com", AnswerStatus.CORRECT),
                new Answer(10L, 1L, "audio10.com", AnswerStatus.INCORRECT),
                new Answer(11L, 1L, "audio11.com", AnswerStatus.INCORRECT),
                new Answer(12L, 1L, "audio12.com", AnswerStatus.INCORRECT),
                new Answer(13L, 1L, "audio13.com", AnswerStatus.INCORRECT),
                new Answer(14L, 1L, "audio14.com", AnswerStatus.INCORRECT),
                new Answer(15L, 1L, "audio15.com", AnswerStatus.INCORRECT),
                new Answer(1L, 2L, "audio16.com", AnswerStatus.CORRECT),
                new Answer(2L, 2L, "audio17.com", AnswerStatus.CORRECT)
        ));
    }

    @DisplayName("완수 비율을 점검한다.")
    @Test
    void result() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/answers/rate")
                .then().log().all()
                .extract();
        JsonPath json = response.jsonPath();
        // then
        assertThat(json.getDouble("completeRate")).isEqualTo(50.0);
    }
}


