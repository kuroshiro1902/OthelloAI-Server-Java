package com.example.Othello2.gameserver.models.response;

import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.Move;
import com.example.Othello2.gameserver.models.PlayerStats;
import com.example.Othello2.gameserver.models.Cell;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class GameStats {
    private Cell[][] cells;
    private Player currentPlayer;
    private List<Move> validMoves;
    private double evaluationValue = 0;

    private boolean isEnd = false;
    private Player winner = Player.EMPTY;
    private PlayerStats currentPlayerStats = new PlayerStats();
    private PlayerStats opponentPlayerStats = new PlayerStats();

    public GameStats(Cell[][] cells, Player currentPlayer, List<Move> validMoves, double evaluationValue){
        this.cells = cells;
        this.currentPlayer = currentPlayer;
        this.validMoves = validMoves;
        this.evaluationValue = evaluationValue;
    }
}
