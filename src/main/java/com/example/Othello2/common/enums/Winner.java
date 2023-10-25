package com.example.Othello2.common.enums;

public enum Winner {
    BLACK("BLACK"),
    WHITE("WHITE"),
    DRAW("DRAW"),
    NULL(null);


    private final String value;

    Winner(String value) {
        this.value = value;
    }
}
