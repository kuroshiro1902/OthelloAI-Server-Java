package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;

import java.util.List;

import static com.example.Othello2.services.CountCornerDiscOfPlayer.countCornerDiscsOfPlayer;
import static com.example.Othello2.services.CountEdgeDiscOfPlayerService.countEdgeDiscsOfPlayer;
import static com.example.Othello2.services.EvaluatePlayerService.evaluatePlayer;
import static com.example.Othello2.services.FindDiscOfPlayerService.findDiscsOfPlayer;
import static com.example.Othello2.services.FindValidMoveService.findValidMoves;

public class DynamicEvaluationService {
    public static int dynamicEvaluation(Cell[][] cells, Player player, List<Move> validMovesForPlayer) {
        System.out.println(validMovesForPlayer);

        PlayerStats currentPlayer = new PlayerStats(player,
                findDiscsOfPlayer(cells, player),
                countEdgeDiscsOfPlayer(cells, player),
                countCornerDiscsOfPlayer(cells, player),
                validMovesForPlayer != null ? validMovesForPlayer.size() : findValidMoves(cells, player).size());

        Player opponent = player == Player.BLACK ? Player.WHITE : Player.BLACK;

        PlayerStats opponentPlayer = new PlayerStats(opponent,
                findDiscsOfPlayer(cells, opponent),
                countEdgeDiscsOfPlayer(cells, opponent),
                countCornerDiscsOfPlayer(cells, opponent),
                findValidMoves(cells, opponent).size());

        System.out.println(currentPlayer);
        System.out.println(opponentPlayer);

        return evaluatePlayer(currentPlayer, opponentPlayer) - evaluatePlayer(opponentPlayer, currentPlayer);
    }
}
