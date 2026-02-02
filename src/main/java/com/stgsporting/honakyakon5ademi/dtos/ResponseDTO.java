package com.stgsporting.honakyakon5ademi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDTO {
    List<Long> questionIds;
    List<String> answerTexts;
    Long quizId;
}
