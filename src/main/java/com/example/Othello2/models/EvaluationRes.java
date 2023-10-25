package com.example.Othello2.models;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.common.enums.Winner;

public class EvaluationRes {
    private PlayerStats curentPlayerStats;
    private PlayerStats opponentPlayerStats;
    private double currentAdvantage;
    private Winner winner;


    public EvaluationRes(PlayerStats currentPlayerStats, PlayerStats opponentPlayerStats, double v, Player winner) {
    }
}
