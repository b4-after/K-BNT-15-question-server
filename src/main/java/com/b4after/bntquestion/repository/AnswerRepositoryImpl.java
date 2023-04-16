package com.b4after.bntquestion.repository;


import com.b4after.bntquestion.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final EntityManager em;
    @Override
    public void save(Answer answer) {
        em.persist(answer);
    }
    @Override
    public List<Answer> findByMemberId(Long memberId) {
        return em.createQuery("select r from Answer r where r.member.id = :memberId", Answer.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
