package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.dto.MemberCreateRequest;
import com.b4after.bntquestion.exception.BusinessException;
import com.b4after.bntquestion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public void saveMember(MemberCreateRequest memberCreateRequest) {
        Member member = new Member(memberCreateRequest.getAge());
        memberRepository.save(member);
    }
}
