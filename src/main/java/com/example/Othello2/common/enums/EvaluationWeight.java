package com.example.Othello2.common.enums;

public enum EvaluationWeight {
    PIECE_AMOUNT(1),
    EDGE_PIECES_AMOUNT(1.5),
    CORNER_PIECES_AMOUNT(2),
    VALID_MOVES_AMOUNT(1.5);

    private final double value;

    EvaluationWeight(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
