package com.stgsporting.honakyakon5ademi.controllers;

import com.stgsporting.honakyakon5ademi.dtos.PaginationDTO;
import com.stgsporting.honakyakon5ademi.dtos.QuizDateDTO;
import com.stgsporting.honakyakon5ademi.services.ResponseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/responses")
public class ResponsesAdminController {
    private final ResponseService responseService;

    public ResponsesAdminController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/all")
    public ResponseEntity<Object> getAllResponses(@RequestParam Integer page, @RequestBody QuizDateDTO dto) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, 10);

        return ResponseEntity.ok(
                new PaginationDTO<>(responseService.getAllResponses(pageable, dto.getDate()))
        );
    }

    @PostMapping("/by-khedma")
    public ResponseEntity<Object> getResponsesByKhedma(@RequestParam Integer page, @RequestParam Long khedmaId, @RequestBody QuizDateDTO dto) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, 10);

        return ResponseEntity.ok(
                new PaginationDTO<>(responseService.getResponsesByKhedma(pageable, dto.getDate(), khedmaId))
        );
    }
}
