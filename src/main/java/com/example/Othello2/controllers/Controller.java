package com.example.Othello2.controllers;

import com.example.Othello2.GameServices.*;
import com.example.Othello2.models.request.MoveRequest;
import com.example.Othello2.models.response.GameStats;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/othello")
@AllArgsConstructor
@CrossOrigin
public class Controller {

    private final InitializeChessBoardService initializeChessBoardService;
    private final FindValidMoveService findValidMoveService;
    private final MoveService moveService;

    @PostMapping("/initialize")
    @Operation(summary = "Khởi tạo bàn cờ")
    public GameStats initializeChessBoard(@RequestBody MoveRequest request){
        return initializeChessBoardService.initialize(request.getCurrentPlayer());
    }

    @PostMapping ("/move")
    GameStats move(@RequestBody MoveRequest request){
        return moveService.move(request.getCells(),request.getCurrentPlayer(),request.getMove());
    }

}
