package com.example.Othello2.common.enums;

public enum Player {
    EMPTY("EMPTY"),
    WHITE("WHITE"),
    BLACK("BLACK");

    private final String value;

    Player(String value) {
        this.value = value;
    }
}
