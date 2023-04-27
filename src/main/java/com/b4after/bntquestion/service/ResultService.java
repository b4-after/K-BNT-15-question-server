package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.Member;
import com.b4after.bntquestion.domain.Question;
import com.b4after.bntquestion.dto.BntResultResponse;
import com.b4after.bntquestion.dto.BntResultResponseMapper;
import com.b4after.bntquestion.exception.BusinessException;
import com.b4after.bntquestion.repository.AnswerRepository;
import com.b4after.bntquestion.repository.MemberRepository;
import com.b4after.bntquestion.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public BntResultResponse findResult(Long memberId) {
        List<Answer> answers = answerRepository.findByMemberId(memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("검사를 진행한 사용자 ID가 아닙니다."));
        List<Question> questions = questionRepository.findAllById(extractQuestionIds(answers));
        return BntResultResponseMapper.toDto(member, questions, answers);
    }

    private List<Long> extractQuestionIds(List<Answer> answers) {
        return answers.stream()
                .map(Answer::getQuestionId)
                .collect(Collectors.toList());
    }
}




