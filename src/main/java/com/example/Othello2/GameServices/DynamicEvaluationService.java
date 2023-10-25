package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.EvaluationRes;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;
import com.example.Othello2.utils.Utils;
import com.example.Othello2.GameServices.CheckWinService;
import com.example.Othello2.GameServices.FindValidMoveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Othello2.common.enums.BoardSize.EBoardSize;

@AllArgsConstructor
@Service

public class DynamicEvaluationService{
    public String dynamicEvaluation(
            Cell[][] cells,
            Player currentPlayer,
            List<Move> validMovesForCurrentPlayer
    ) {
        Player opponentPlayer = Utils.enemyOf(currentPlayer);
        PlayerStats currentPlayerStats = new PlayerStats();
        currentPlayerStats.pieces = new ArrayList<>();
        currentPlayerStats.player = currentPlayer;

        PlayerStats opponentPlayerStats = new PlayerStats();
        opponentPlayerStats.pieces = new ArrayList<>();
        opponentPlayerStats.player = opponentPlayer;
        //counter
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                // Current player
                if (cell.piece.equals(currentPlayer)) {
                    currentPlayerStats.pieces.add(cell);
                    String edgeOrCorner;
                    edgeOrCorner = isCellAtEdgeOrCorner(cell);
                    if (edgeOrCorner.equals("corner")) {
                        currentPlayerStats.cornerPiecesAmount++;
                    }
                    if (edgeOrCorner.equals("edge")) {
                        currentPlayerStats.edgePiecesAmount++;
                    }
                    currentPlayerStats.validMovesAmount = validMovesForCurrentPlayer != null ?
                            validMovesForCurrentPlayer.size() : FindValidMoveService.findValidMoves(cells, currentPlayer).size();
                    opponentPlayerStats.validMovesAmount = FindValidMoveService.findValidMoves(cells, opponentPlayer).size();
                }
                Player winner = CheckWinService.checkWinOfPlayer(currentPlayerStats.pieces, currentPlayerStats.player);

                if (winner.equals(currentPlayerStats.player)) {
                    currentPlayerStats.evaluationValue = 10 * 10;
                    opponentPlayerStats.evaluationValue = -10 * 10;
                } else if (winner.equals(opponentPlayerStats.player)) {
                    currentPlayerStats.evaluationValue = -10 * 10;
                    opponentPlayerStats.evaluationValue = 10 * 10;
                }
                currentPlayerStats.evaluate();
                opponentPlayerStats.evaluate();
                return new EvaluationRes(currentPlayerStats, opponentPlayerStats,
                        currentPlayerStats.evaluationValue - opponentPlayerStats.evaluationValue, winner).toString();
            }
            }
            public String isCellAtEdgeOrCorner(Cell ) {
            int x = Cell.getX();
            int y = Cell.getY();
            boolean isXEdge = x == 0 || x == EBoardSize - 1;
            boolean isYEdge = y == 0 || y == EBoardSize - 1;
            if (isXEdge && !isYEdge) {
                return "edge"; // Ô nằm ở biên theo chiều ngang
            }
            if (isYEdge && !isXEdge) {
                return "edge"; // Ô nằm ở biên theo chiều dọc
            }
            if (isYEdge && isXEdge) {
                return "corner"; // Ô nằm ở góc
            }
            return null;
        }

