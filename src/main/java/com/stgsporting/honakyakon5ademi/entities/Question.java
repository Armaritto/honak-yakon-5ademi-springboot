package com.stgsporting.honakyakon5ademi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stgsporting.honakyakon5ademi.config.DatabaseEnum;
import com.stgsporting.honakyakon5ademi.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DatabaseEnum.questionsTable)
public class Question extends BaseEntity {
    @Column(name = DatabaseEnum.text, nullable = false)
    private String text;

    @Column(name = DatabaseEnum.type, nullable = false)
    private Type type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = DatabaseEnum.quizId, nullable = false)
    private Quiz quiz;
}
