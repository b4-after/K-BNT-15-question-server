package com.b4after.bntquestion;

import com.b4after.bntquestion.service.AnswerAudioUploader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BntQuestionApplicationTests {

	@MockBean
	private AnswerAudioUploader answerAudioUploader;

	@Test
	void contextLoads() {
	}

}
