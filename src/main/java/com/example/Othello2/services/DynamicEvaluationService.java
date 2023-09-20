package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Othello2.services.CountCornerPieceOfPlayer.countCornerPiecesOfPlayer;
import static com.example.Othello2.services.CountEdgePieceOfPlayerService.countEdgePiecesOfPlayer;

@AllArgsConstructor
@Service
public class DynamicEvaluationService {

    private final FindValidMoveService findValidMoveService;
    private final EvaluatePlayerService evaluatePlayerService;
    private final GetPieceService getPieceService;

    public double dynamicEvaluation(Cell[][] cells, Player player, List<Move> validMovesForPlayer) {
        System.out.println(validMovesForPlayer);

        PlayerStats currentPlayer = new PlayerStats(player,
                getPieceService.getAllPiecesOfThisPlayer(cells, player),
                countEdgePiecesOfPlayer(cells, player),
                countCornerPiecesOfPlayer(cells, player),
                validMovesForPlayer != null ? validMovesForPlayer.size()
                        : findValidMoveService.findValidMoves(cells, player).size());

        Player opponent = player == Player.BLACK ? Player.WHITE : Player.BLACK;

        PlayerStats opponentPlayer = new PlayerStats(opponent,
                getPieceService.getAllPiecesOfThisPlayer(cells, opponent),
                countEdgePiecesOfPlayer(cells, opponent),
                countCornerPiecesOfPlayer(cells, opponent),
                findValidMoveService.findValidMoves(cells, opponent).size());

        System.out.println(currentPlayer);
        System.out.println(opponentPlayer);

        return evaluatePlayerService.evaluatePlayer(currentPlayer, opponentPlayer) -
                evaluatePlayerService.evaluatePlayer(opponentPlayer, currentPlayer);
    }
}
