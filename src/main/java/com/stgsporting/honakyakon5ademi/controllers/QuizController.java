package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.QuizDateDTO;
import com.stgsporting.honakyakon5ademi.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getQuiz(@RequestParam Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @GetMapping("/today")
    public ResponseEntity<Object> getTodayQuiz() {
        return ResponseEntity.ok(quizService.getTodayQuiz());
    }

    @PostMapping("/previous")
    public ResponseEntity<Object> getPreviousQuiz(@RequestBody QuizDateDTO dto) {
        return ResponseEntity.ok(quizService.getPreviousQuiz(dto.getDate()));
    }

    @PostMapping("/date")
    public ResponseEntity<Object> getQuizByDate(@RequestBody QuizDateDTO dto) {
        return ResponseEntity.ok(quizService.getQuizByDate(dto.getDate()));
    }

    @GetMapping("/solved")
    public ResponseEntity<Object> getSolvedDates() {
        return ResponseEntity.ok(quizService.getSolvedDates());
    }
}
