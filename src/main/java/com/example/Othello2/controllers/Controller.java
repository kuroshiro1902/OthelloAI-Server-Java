package com.example.Othello2.controllers;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.GameServices.*;
import com.example.Othello2.models.request.CheckWinRequest;
import com.example.Othello2.models.request.DynamicEvaluationRequest;
import com.example.Othello2.models.request.FindValidMoveRequest;
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
    private final GetPieceService getPieceService;
    private final IsValidMovesService isValidMovesService;
    private final DynamicEvaluationService dynamicEvaluationService;
    private final FindValidMoveService findValidMoveService;
    private final MoveService moveService;
    private final CheckWinService checkWinService;
    @PostMapping("/dynamic-evaluation")
    @Operation(summary = "Đánh giá lợi thế")
    public Double dynamicEvaluation(@RequestBody DynamicEvaluationRequest request){
        return dynamicEvaluationService.dynamicEvaluation(request.getCells(),
                Player.valueOf(request.getPlayer()),
                request.getValidMovesForPlayer());
    }

    @PostMapping("/initialize")
    @Operation(summary = "Khởi tạo bàn cờ")
    public GameStats initializeChessBoard(@RequestBody MoveRequest request){
        return initializeChessBoardService.initialize(request.getCurrentPlayer());
    }

    @PostMapping("/find-valid-move")
    List<Move> findValidMoves(@RequestBody FindValidMoveRequest request){
        return findValidMoveService.findValidMoves(request.getCells(), Player.valueOf(request.getPlayer()));
    }

    @PostMapping ("/move")
    GameStats move(@RequestBody MoveRequest request){
        return moveService.move(request.getCells(),request.getCurrentPlayer(),request.getMove());
    }

    @PostMapping("/checkwin")
    Player checkwin(@RequestBody CheckWinRequest request){
        return checkWinService.checkWinOfPlayer(request.getCells(),request.getCurrentPlayer());
    }
}
