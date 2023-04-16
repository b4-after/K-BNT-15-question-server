package com.b4after.bntquestion.repository;



import com.b4after.bntquestion.domain.Answer;

import java.util.List;

public interface AnswerRepository {
    void save(Answer answer);

    List<Answer> findByMemberId(Long memberID);

}
