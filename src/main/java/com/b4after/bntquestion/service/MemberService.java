package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Member;
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

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("해당 사용자는 존재하지 않습니다."));
    }
}
