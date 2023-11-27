package com.example.Othello2.common.enums;

public enum EvaluationWeight {
    PIECE_AMOUNT(10),
    EDGE_PIECES_AMOUNT(25),
    CORNER_PIECES_AMOUNT(50),
    VALID_MOVES_AMOUNT(20);

    private final double value;

    EvaluationWeight(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
