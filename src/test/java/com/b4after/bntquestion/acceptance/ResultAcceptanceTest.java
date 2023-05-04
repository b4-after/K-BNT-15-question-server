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
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ResultAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void executeBNT() {
        answerRepository.saveAll(List.of(
                new Answer(1L, memberId, "audio1.com", AnswerStatus.CORRECT),
                new Answer(2L, memberId, "audio2.com", AnswerStatus.CORRECT),
                new Answer(3L, memberId, "audio3.com", AnswerStatus.CORRECT),
                new Answer(4L, memberId, "audio4.com", AnswerStatus.CORRECT),
                new Answer(5L, memberId, "audio5.com", AnswerStatus.CORRECT),
                new Answer(6L, memberId, "audio6.com", AnswerStatus.CORRECT),
                new Answer(7L, memberId, "audio7.com", AnswerStatus.CORRECT),
                new Answer(8L, memberId, "audio8.com", AnswerStatus.CORRECT),
                new Answer(9L, memberId, "audio9.com", AnswerStatus.CORRECT),
                new Answer(10L, memberId, "audio10.com", AnswerStatus.INCORRECT),
                new Answer(11L, memberId, "audio11.com", AnswerStatus.INCORRECT),
                new Answer(12L, memberId, "audio12.com", AnswerStatus.INCORRECT),
                new Answer(13L, memberId, "audio13.com", AnswerStatus.INCORRECT),
                new Answer(14L, memberId, "audio14.com", AnswerStatus.INCORRECT),
                new Answer(15L, memberId, "audio15.com", AnswerStatus.INCORRECT)
        ));
    }

    @DisplayName("BNT 결과를 조회한다.")
    @Test
    void result() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/results/" + memberId)
                .then().log().all()
                .extract();

        // then
        JsonPath json = response.jsonPath();
        List<String> questionWords = json.getList("results.word", String.class);
        List<String> isCorrects = json.getList("results.isCorrect", String.class);
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),

                () -> assertThat(json.getInt("totalScore")).isEqualTo(9),
                () -> assertThat(json.getDouble("averageScore")).isEqualTo(12.37),

                () -> assertThat(questionWords).containsExactly(
                        "용", "올챙이", "거미줄", "눈사람", "장화",
                        "거북선", "고드름", "낙타", "골무", "소화기",
                        "인어", "공룡", "유모차", "토시", "불가사리"
                ),
                () -> assertThat(isCorrects).containsExactly(
                        "O", "O", "O", "O", "O", "O", "O", "O", "O",
                        "X", "X", "X", "X", "X", "X"
                )
        );
    }

    @DisplayName("없는 회원 ID로 결과를 조회하면 400 에러가 발생한다.")
    @Test
    void result_notFoundMember() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/results/" +  2)
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
