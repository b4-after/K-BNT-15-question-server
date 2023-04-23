package com.b4after.bntquestion.repository;


import com.b4after.bntquestion.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
