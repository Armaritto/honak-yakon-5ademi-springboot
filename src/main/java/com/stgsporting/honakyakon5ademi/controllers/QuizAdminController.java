package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.QuizDTO;
import com.stgsporting.honakyakon5ademi.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quiz")
public class QuizAdminController {
    private final QuizService quizService;

    public QuizAdminController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createQuiz(@RequestBody QuizDTO quizDTO) {
        quizService.createQuiz(quizDTO);
        return ResponseEntity.ok("Quiz Created Successfully");
    }

    @DeleteMapping("")
    public ResponseEntity<Object> editQuiz(@RequestParam Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz Deleted Successfully");
    }
}
