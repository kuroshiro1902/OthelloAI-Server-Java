package com.example.Othello2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Move {
    private Cell[] froms;
    private Cell to;
    private Cell[] flips;
}
