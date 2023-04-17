package com.b4after.bntquestion.repository;


import com.b4after.bntquestion.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final EntityManager em;

    @Override
    public Member findMember(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return member;
    }
}
