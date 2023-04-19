package com.b4after.bntquestion.repository;


import com.b4after.bntquestion.domain.Member;

public interface MemberRepository {
    Member findMember(Long memberId);
}
