package com.example.Othello2.common.enums;

public enum EvaluationWeight {
    DISC_AMOUNT(1),
    EDGE_DISCS_AMOUNT(1.5),
    CORNER_DISCS_AMOUNT(2),
    VALID_MOVES_AMOUNT(1.5);

    private final double value;

    EvaluationWeight(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
