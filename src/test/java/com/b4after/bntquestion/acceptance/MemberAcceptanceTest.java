package com.b4after.bntquestion.acceptance;

import com.b4after.bntquestion.dto.MemberCreateRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MemberAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원을 생성한다.")
    @Test
    void createMember() {
        // given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest();
        memberCreateRequest.setAge(56);
        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(memberCreateRequest)
                .when()
                .post("/members/")
                .then().log().all()
                .extract();
        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(response.headers().get("Location").getValue()).isEqualTo("/members/2")
        );
    }
}
