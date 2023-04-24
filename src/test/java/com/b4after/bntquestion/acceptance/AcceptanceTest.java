package com.b4after.bntquestion.acceptance;

import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Question;
import com.b4after.bntquestion.repository.AnswerRepository;
import com.b4after.bntquestion.repository.MemberRepository;
import com.b4after.bntquestion.repository.QuestionRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected QuestionRepository questionRepository;

    @Autowired
    protected AnswerRepository answerRepository;

    protected Long memberId;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        Member member = memberRepository.save(new Member(58));
        memberId = member.getId();

        List<Question> questions = List.of(
                new Question(1L, "image.com", "용"),
                new Question(2L, "image.com", "올챙이"),
                new Question(3L, "image.com", "거미줄"),
                new Question(4L, "image.com", "눈사람"),
                new Question(5L, "image.com", "장화"),
                new Question(6L, "image.com", "거북선"),
                new Question(7L, "image.com", "고드름"),
                new Question(8L, "image.com", "낙"),
                new Question(9L, "image.com", "골무"),
                new Question(10L, "image.com", "소화기"),
                new Question(11L, "image.com", "인어"),
                new Question(12L, "image.com", "공룡"),
                new Question(13L, "image.com", "유모차"),
                new Question(14L, "image.com", "토시"),
                new Question(15L, "image.com", "불가사리")
        );
        questionRepository.saveAll(questions);
    }
}
