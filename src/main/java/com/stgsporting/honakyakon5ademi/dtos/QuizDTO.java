package com.stgsporting.honakyakon5ademi.dtos;

import com.stgsporting.honakyakon5ademi.entities.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuizDTO {
    private Long id;
    private List<QuestionDTO> questionDTOS;
    private Date date;
    private String name;

    public void setQuestionDTOManually(List<Question> questions) {
        questionDTOS = new ArrayList<>();
        for (Question q : questions) {
            questionDTOS.add(new QuestionDTO(q.getId(), q.getText(),q.getType()));
        }
    }
}
