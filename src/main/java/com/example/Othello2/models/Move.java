package com.example.Othello2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Move {
    private List<Cell> froms;
    private Cell to;
    private List<Cell> flips;
}
