package com.example.Othello2.services;

import com.example.Othello2.models.Cell;
import com.example.Othello2.models.PlayerStats;
import com.example.Othello2.common.enums.EvaluationWeight;
public class EvaluatePlayerService {

    public static int evaluatePlayer(PlayerStats playerStats, PlayerStats opponentStats) {
        int positionValue = 0;
        for (Cell cell : playerStats.getDiscs()) {
            positionValue += staticHValues[cell.getX()][cell.getY()];
        }

        double discRatio = (double) playerStats.getDiscs().size() / opponentStats.getDiscs().size();
        System.out.println(positionValue);

        int discAmountValue = playerStats.getDiscs().size() * evaluationWeight.discAmount;
        int edgeDiscsValue = playerStats.getEdgeDiscsAmount() * evaluationWeight.edgeDiscsAmount;
        int cornerDiscsValue = playerStats.getCornerDiscsAmount() * evaluationWeight.cornerDiscsAmount;
        int validMovesValue = playerStats.getValidMovesAmount() * evaluationWeight.validMovesAmount;

        return positionValue + (int) discRatio + discAmountValue + edgeDiscsValue + cornerDiscsValue + validMovesValue;
    }
}
