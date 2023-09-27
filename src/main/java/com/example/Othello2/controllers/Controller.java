package com.example.Othello2.controllers;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.GameServices.*;
import com.example.Othello2.models.request.DynamicEvaluationRequest;
import com.example.Othello2.models.request.FindValidMoveRequest;
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
    private final GetPieceService getPieceService;
    private final IsValidMovesService isValidMovesService;
    private final DynamicEvaluationService dynamicEvaluationService;
    private final FindValidMoveService findValidMoveService;

    @PostMapping("/dynamic-evaluation")
    @Operation(summary = "Đánh giá lợi thế")
    public Double dynamicEvaluation(@RequestBody DynamicEvaluationRequest request){
        return dynamicEvaluationService.dynamicEvaluation(request.getCells(),
                Player.valueOf(request.getPlayer()),
                request.getValidMovesForPlayer());
    }

    @GetMapping("/initialize")
    @Operation(summary = "Khởi tạo bàn cờ")
    public Cell[][] initializeChessBoard(){
        return initializeChessBoardService.initialize();
    }

    @PostMapping("/find-valid-move")
    List<Move> findValidMoves(@RequestBody FindValidMoveRequest request){
        return findValidMoveService.findValidMoves(request.getCells(), Player.valueOf(request.getPlayer()));
    }
}
