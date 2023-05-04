package com.b4after.bntquestion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionResponse {
    private String imageUrl;

    public QuestionResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

