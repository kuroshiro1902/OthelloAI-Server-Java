package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckWinService {
    private final FindValidMoveService findValidMoveService;
    public Player checkWinOfPlayer(Cell[][] cells, PlayerStats currentPlayerStats, PlayerStats opponentStats){
            // đếm tổng số quân cờ
            int piceOfPlayer = currentPlayerStats.getPieces().size();
            int piceOfOpponent = opponentStats.getPieces().size();
            if(piceOfPlayer + piceOfOpponent == 64){
                if(piceOfPlayer > piceOfOpponent) return currentPlayerStats.getPlayer();
                else return opponentStats.getPlayer();
            }
            // kiểm tra nước đi hợp lệ
            List<Move> validMovesOfPlayer = findValidMoveService.findValidMoves(cells,currentPlayerStats.getPlayer());
            List<Move> validMoveOfOpponents = findValidMoveService.findValidMoves(cells,opponentStats.getPlayer());
            if(validMovesOfPlayer.size()<= 0 && validMoveOfOpponents.size() <= 0){
                if(piceOfPlayer > piceOfOpponent) return currentPlayerStats.getPlayer();
                else return opponentStats.getPlayer();
            }
        return null;
    }
}
