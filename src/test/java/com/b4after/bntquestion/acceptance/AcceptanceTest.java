package com.b4after.bntquestion.acceptance;

import com.b4after.bntquestion.domain.AnswerAudio;
import com.b4after.bntquestion.service.AnswerAudioUploader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.BDDMockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AcceptanceTest {

    @LocalServerPort
    private int port;

    protected final Long memberId = 1L;

    @MockBean
    private AnswerAudioUploader answerAudioUploader;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        given(answerAudioUploader.upload(any()))
                .willReturn(new AnswerAudio("mock-audio-file-object-key"));
    }
}
