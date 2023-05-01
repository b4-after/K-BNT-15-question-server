package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.dto.MemberCreateRequest;
import com.b4after.bntquestion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public HttpHeaders saveMember(MemberCreateRequest memberCreateRequest) {
        Member member = new Member(memberCreateRequest.getAge());
        memberRepository.save(member);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/members/" + member.getId());
        return headers;
    }
}
