package com.example.Othello2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinimaxResult {
    private Move bestMove;
    private double evaluationValue;
}
