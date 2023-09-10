package com.example.Othello2.controllers;

import com.example.Othello2.models.Cell;
import com.example.Othello2.services.InitializeChessBoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/othello")
@AllArgsConstructor
public class Controller {

    private final InitializeChessBoardService initializeChessBoardService;

    @GetMapping("/initialize")
    public Cell[][] initializeChessBoard(){
        return initializeChessBoardService.initialize();
    }

}
