package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Result;
import com.b4after.bntquestion.repository.AnswerRepository;
import com.b4after.bntquestion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public Result findResult(Long memberId) {
        List<Answer> answers = answerRepository.findByMemberId(memberId);
        Member member = memberRepository.findMember(memberId);
        return new Result(member, answers);
    }
}




