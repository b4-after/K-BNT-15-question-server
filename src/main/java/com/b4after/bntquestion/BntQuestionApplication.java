package com.b4after.bntquestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BntQuestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BntQuestionApplication.class, args);
	}

}
