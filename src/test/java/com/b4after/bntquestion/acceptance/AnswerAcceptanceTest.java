package com.b4after.bntquestion.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswerAcceptanceTest extends AcceptanceTest {

    @DisplayName("BNT 정답을 저장한다")
    @Test
    void saveAnswer() {
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("audio", "audio-file-name", "audio-byte".getBytes())
                .formParam("memberId", memberId)
                .formParam("questionId", 1L)
                .post("/answers")
                .then().log().all()
                .extract();

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(response.headers().get("Location").getValue()).isEqualTo("/answers/1")
        );
    }
}
