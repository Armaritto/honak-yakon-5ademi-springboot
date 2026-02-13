package com.stgsporting.honakyakon5ademi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    String username;
    String khedmaName;
    List<String> answerTexts;
    Boolean checkbox;

    public UserResponseDTO (String username, String khedmaName, String answerTexts, Boolean checkbox) {
        this.username = username;
        this.khedmaName = khedmaName;
        this.answerTexts = (answerTexts != null && !answerTexts.isEmpty()) 
            ? Arrays.asList(answerTexts.split(",")) 
            : List.of();
        this.checkbox = checkbox;
    }
}
