package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.Answer;
import com.b4after.bntquestion.domain.AnswerAudio;
import com.b4after.bntquestion.dto.AnswerCreateRequest;
import com.b4after.bntquestion.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerAudioUploader answerAudioUploader;

    @Transactional
    public Long createAnswer(AnswerCreateRequest request) {
        AnswerAudio answerAudio = answerAudioUploader.upload(request.getAudio());
        Answer answer = Answer.createBeforeGrading(request.getQuestionId(), request.getMemberId(), answerAudio);
        answerRepository.save(answer);
        return answer.getId();
    }
}
