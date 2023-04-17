package com.b4after.bntquestion.service;


import com.b4after.bntquestion.domain.*;
import com.b4after.bntquestion.repository.AnswerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
class ResultServiceTest {

    @Autowired
    ResultService resultService;

    @Test
    void getResultTest() throws JsonProcessingException {
        Result result = resultService.findResult(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);
    }



}