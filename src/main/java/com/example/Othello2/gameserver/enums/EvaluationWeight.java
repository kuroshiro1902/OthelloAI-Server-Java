package com.example.Othello2.gameserver.enums;

public enum EvaluationWeight {
    PIECE_AMOUNT(3),
    EDGE_PIECES_AMOUNT(2),
    CORNER_PIECES_AMOUNT(2.5),
    VALID_MOVES_AMOUNT(2.5);

    private final double value;

    EvaluationWeight(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
