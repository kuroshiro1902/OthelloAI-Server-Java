package com.example.Othello2.gameserver.models;

import com.example.Othello2.gameserver.enums.Player;
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
