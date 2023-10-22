package com.example.Othello2.models;

public class MinimaxResult {
    private Move bestMove;
    private double evaluationValue;

    public MinimaxResult(Move bestMove, double evaluationValue) {
        this.bestMove = bestMove;
        this.evaluationValue = evaluationValue;
    }

    public Move getBestMove() {
        return bestMove;
    }

    public double getEvaluationValue() {
        return evaluationValue;
    }
}
