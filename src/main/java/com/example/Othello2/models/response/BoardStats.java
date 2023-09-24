package com.example.Othello2.models.response;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class BoardStats {
    private Cell[][] newCells;
    private Player newCurrentPlayer;
    private Move[] newValidMoves;
    private double EvaluationValue;

}
