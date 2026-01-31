package com.stgsporting.honakyakon5ademi.entities;

import com.stgsporting.honakyakon5ademi.config.DatabaseEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DatabaseEnum.responsesTable)
public class Response extends BaseEntity {
    @Column(name = DatabaseEnum.answer, nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = DatabaseEnum.questionId, nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = DatabaseEnum.userId, nullable = false)
    private User user;
}
