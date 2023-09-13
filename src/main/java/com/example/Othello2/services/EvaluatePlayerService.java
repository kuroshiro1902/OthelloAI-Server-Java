package com.example.Othello2.services;

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
        for (Cell cell : playerStats.getDiscs()) {
            positionValue += heuristicValueArray.heuristicValueArray()[cell.getX()][cell.getY()];
        }

        double discRatio = (double) playerStats.getDiscs().size() / opponentStats.getDiscs().size();
        System.out.println(positionValue);

        double discAmountValue = playerStats.getDiscs().size() * EvaluationWeight.DISC_AMOUNT.getValue();
        double edgeDiscsValue = playerStats.getEdgeDiscsAmount() * EvaluationWeight.EDGE_DISCS_AMOUNT.getValue();
        double cornerDiscsValue = playerStats.getCornerDiscsAmount() * EvaluationWeight.CORNER_DISCS_AMOUNT.getValue();
        double validMovesValue = playerStats.getValidMovesAmount() * EvaluationWeight.VALID_MOVES_AMOUNT.getValue();

        return positionValue + discRatio + discAmountValue + edgeDiscsValue + cornerDiscsValue + validMovesValue;
    }
}
