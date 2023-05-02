package com.b4after.bntquestion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class AnswerCreateRequest {

    private Long memberId;
    private Long questionId;
    private MultipartFile audio;
}
