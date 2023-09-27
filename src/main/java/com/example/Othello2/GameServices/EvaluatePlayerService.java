package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.EvaluationWeight;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.PlayerStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EvaluatePlayerService {
    private final HeuristicValueArray heuristicValueArray;

    public Double evaluatePlayer(PlayerStats playerStats, PlayerStats opponentStats) {
        int positionValue = 0;
        for (Cell cell : playerStats.getPieces()) {
            positionValue += heuristicValueArray.heuristicValueArray()[cell.getX()][cell.getY()];
        }

        double pieceRatio = (double) playerStats.getPieces().size() / opponentStats.getPieces().size();
        System.out.println(positionValue);

        double pieceAmountValue = playerStats.getPieces().size() * EvaluationWeight.PIECE_AMOUNT.getValue();
        double edgePiecesValue = playerStats.getEdgePiecesAmount() * EvaluationWeight.EDGE_PIECES_AMOUNT.getValue();
        double cornerPiecesValue = playerStats.getCornerPiecesAmount() * EvaluationWeight.CORNER_PIECES_AMOUNT.getValue();
        double validMovesValue = playerStats.getValidMovesAmount() * EvaluationWeight.VALID_MOVES_AMOUNT.getValue();

        return positionValue + pieceRatio + pieceAmountValue + edgePiecesValue + cornerPiecesValue + validMovesValue;
    }
}
