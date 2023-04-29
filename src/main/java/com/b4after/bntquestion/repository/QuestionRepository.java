package com.b4after.bntquestion.repository;

import com.b4after.bntquestion.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
