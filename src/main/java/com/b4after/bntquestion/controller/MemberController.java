package com.b4after.bntquestion.controller;

import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/")
    public ResponseEntity<Member> saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        return ResponseEntity.ok().body(member);
    }
}
