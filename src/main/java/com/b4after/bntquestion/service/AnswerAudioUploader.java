package com.b4after.bntquestion.service;

import com.b4after.bntquestion.domain.AnswerAudio;
import org.springframework.web.multipart.MultipartFile;

public interface AnswerAudioUploader {

    AnswerAudio upload(MultipartFile audioFile);
}
