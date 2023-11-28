package com.example.Othello2.gameserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinimaxResult {
    private Move bestMove;
    private double evaluationValue;
    private PositionCount positionCount = new PositionCount(0);

    public MinimaxResult(Move bestMove, double evaluationValue){
        this.bestMove = bestMove;
        this.evaluationValue = evaluationValue;
    }
}
