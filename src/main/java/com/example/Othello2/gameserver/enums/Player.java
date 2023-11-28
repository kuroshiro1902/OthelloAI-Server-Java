package com.example.Othello2.gameserver.enums;

public enum Player {
    EMPTY("EMPTY"), //Rỗng | Không ai win
    WHITE("WHITE"),
    BLACK("BLACK"),

    BOTH("BOTH"); //Hòa

    private final String value;

    Player(String value) {
        this.value = value;
    }
}
