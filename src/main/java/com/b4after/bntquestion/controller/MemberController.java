package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.dto.MemberCreateRequest;
import com.b4after.bntquestion.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> saveMember(@RequestBody MemberCreateRequest memberCreateRequest) {
        Long memberId = memberService.saveMember(memberCreateRequest);
        return ResponseEntity.created(URI.create("/members/" + memberId))
                .build();
    }
}
