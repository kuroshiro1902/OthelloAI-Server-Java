package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.response.GameStats;
import com.example.Othello2.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MoveService {
    private final Utils utils;
    private final FindValidMoveService findValidMoveService;
    public GameStats move(Cell[][] cells, Player currentPlayer, Move move){
        Player newCurrentPlayer;
        if(move != null){
            newCurrentPlayer = utils.enemyOf(currentPlayer);
            cells[move.getTo().getX()][move.getTo().getY()].setPiece(currentPlayer);
            for(Cell cell: move.getFlips()){
                cells[cell.getX()][cell.getY()].setPiece(currentPlayer);
            }
        }
        else{
            newCurrentPlayer = currentPlayer;
        }

        List<Move> newValidMoves = findValidMoveService.findValidMoves(cells, newCurrentPlayer);
        return new GameStats(cells, newCurrentPlayer, newValidMoves, 0);
    }
}
