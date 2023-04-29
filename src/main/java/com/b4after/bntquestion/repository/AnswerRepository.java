package com.b4after.bntquestion.repository;



import com.b4after.bntquestion.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByMemberId(Long memberId);
}
