package com.stgsporting.honakyakon5ademi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuizDTO {
    private List<QuestionDTO> questionDTOS;
    private Date date;
}
