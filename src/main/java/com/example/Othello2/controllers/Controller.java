package com.example.Othello2.controllers;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.services.GetDiscService;
import com.example.Othello2.services.InitializeChessBoardService;
import com.example.Othello2.services.InsideBoardCheckService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/othello")
@AllArgsConstructor
public class Controller {

    private final InitializeChessBoardService initializeChessBoardService;
    private final InsideBoardCheckService insideBoardCheckService;
    private final GetDiscService getDiscService;

    @GetMapping("/initialize")
    @Operation(summary = "Khởi tạo bàn cờ")
    public Cell[][] initializeChessBoard(){
        return initializeChessBoardService.initialize();
    }

    @GetMapping("/inside-board")
    @Operation(summary = "Kiểm tra xem quân cờ có nằm trong bàn cờ không")
    public Boolean insideBoardCheck(@RequestParam @NonNull Integer x,
                                    @RequestParam @NonNull Integer y){
        return insideBoardCheckService.check(x, y);
    }

    @Operation(summary = "Lấy tất cả quân cờ của người chơi hiện tại")
    @PostMapping("/all-discs-of-player")
    public List<Cell> getAllDiscsOfThisPlayer(@RequestBody Cell[][] cells,
                                              @RequestParam @NonNull String player){
        return getDiscService.getAllDiscsOfThisPlayer(cells, Player.valueOf(player));
    }
}
