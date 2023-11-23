package com.example.Othello2.models;

import com.example.Othello2.common.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EvaluationRes {
    private PlayerStats currentPlayerStats;
    private PlayerStats opponentPlayerStats;
    private double currentAdvantage;
    private Player winner;
}
