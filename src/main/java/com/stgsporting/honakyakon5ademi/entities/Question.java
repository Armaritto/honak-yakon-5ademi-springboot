package com.stgsporting.honakyakon5ademi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stgsporting.honakyakon5ademi.config.DatabaseEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DatabaseEnum.questionsTable)
public class Question extends BaseEntity {
    @Column(name = DatabaseEnum.text, nullable = false)
    private String text;

    @Column(name = DatabaseEnum.date, nullable = false)
    private Date date;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Response> responses;
}
