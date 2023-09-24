package com.example.Othello2.controllers;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.GameServices.*;
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
    public Double dynamicEvaluation(@RequestBody Cell[][] cells,@RequestParam String player,@RequestBody List<Move> validMovesForPlayer){
        return dynamicEvaluationService.dynamicEvaluation(cells, Player.valueOf(player), validMovesForPlayer);
    }

    @GetMapping("/initialize")
    @Operation(summary = "Khởi tạo bàn cờ")
    public Cell[][] initializeChessBoard(){
        return initializeChessBoardService.initialize();
    }

//    @Operation(summary = "Lấy tất cả quân cờ của người chơi hiện tại")
//    @PostMapping("/all-discs-of-player")
//    public List<Cell> getAllDiscsOfThisPlayer(@RequestBody Cell[][] cells,
//                                              @RequestParam @NonNull String player){
//        return getDiscService.getAllDiscsOfThisPlayer(cells, Player.valueOf(player));
//    }

    @PostMapping("/is-valid-move")
    public Move isValidMove(@RequestBody Cell[][] cells,
                            @RequestParam int row,
                            @RequestParam int col,
                            @RequestParam String player){
        return isValidMovesService.isValidMoves(cells, row, col, Player.valueOf(player));
    }

    @PostMapping("/find-valid-move")
    List<Move> findValidMoves(@RequestBody Cell[][] cells, @RequestParam String player){
        return findValidMoveService.findValidMoves(cells, Player.valueOf(player));
    }


}
