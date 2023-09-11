package com.example.Othello2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Move {
    private Cell from;
    private Cell to;
    private Cell[] flips;
}
