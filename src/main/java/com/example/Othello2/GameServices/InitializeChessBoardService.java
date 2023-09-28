package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.response.GameStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class InitializeChessBoardService {
    private static final Integer size = 8;
    private final FindValidMoveService findValidMoveService;
    public GameStats initialize(Player firstPlayer){
        Cell[][] cells = new Cell[size][size];
        int centerIndex = size/2 - 1;

        for (int x = 0 ; x < size; x++){
            for (int y = 0 ; y < size; y++){
                cells[x][y] = new Cell(x, y, Player.EMPTY);
            }
        }

        cells[centerIndex][centerIndex].setPiece(Player.WHITE);
        cells[centerIndex][centerIndex + 1].setPiece(Player.BLACK);
        cells[centerIndex + 1][centerIndex].setPiece(Player.BLACK);
        cells[centerIndex + 1][centerIndex + 1].setPiece(Player.WHITE);

        Move[] validMoves = findValidMoveService.findValidMoves(cells,firstPlayer).toArray(new Move[0]);
        return new GameStats(cells, firstPlayer, validMoves,0 );
    }
}
