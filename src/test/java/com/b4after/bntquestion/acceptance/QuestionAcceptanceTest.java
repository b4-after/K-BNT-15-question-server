package com.b4after.bntquestion.acceptance;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionAcceptanceTest extends AcceptanceTest{

    @DisplayName("문제의 이미지 url을 제공한다.")
    @Test
    void Question() {
        // given
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/questions/1")
                .then().log().all()
                .extract();
        // then
        JsonPath json = response.jsonPath();
        assertAll(
                () -> assertThat(json.getString("imageUrl")).isEqualTo("image.com")
        );
    }
}
