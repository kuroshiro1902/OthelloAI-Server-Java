package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.MinimaxResult;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.response.GameStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MiniMaxService {
    private final FindValidMoveService findvalidmoveService;
    private final MoveService moveService;
    private final DynamicEvaluationService dynamicEvaluationService;
    private Cell[][] copyCells(Cell[][] cells) {
        Cell[][] copy = new Cell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                // Implement clone logic for ICell (if available)
                copy[i][j] = new Cell(cells[i][j].getX(),cells[i][j].getY(),cells[i][j].getPiece());
            }
        }
        return copy;
    }

    public MinimaxResult minimax(Cell[][] cells,
                                 int depth,
                                 boolean isMaximizingPlayer,
                                 double evaluationValue,
                                 Player player) {
        List<Move> validMoves = findvalidmoveService.findValidMoves(cells, player);

        // Sort moves randomly
        Collections.shuffle(validMoves);

        Move currMove;
        if (depth == 0 || validMoves.isEmpty()) {
            return new MinimaxResult(null, evaluationValue);
        }

        double maxValue = Double.NEGATIVE_INFINITY;
        double minValue = Double.POSITIVE_INFINITY;
        Move bestMove = null;

        for (Move validMove : validMoves) {
            currMove = validMove;
            GameStats newGameStatsAfterCurrMove = moveService.move(copyCells(cells), player, currMove);

            // Dynamic evaluation and other necessary processing
            EvaluationRes evaluationResult = dynamicEvaluationService.dynamicEvaluation(
                                                        newGameStatsAfterCurrMove.getCells(),
                                                        newGameStatsAfterCurrMove.getCurrentPlayer(),
                                                        newGameStatsAfterCurrMove.getValidMoves());

            // Gọi đệ quy với Minimax
            MinimaxResult childResult = minimax(
                    copyCells(newGameStatsAfterCurrMove.getCells()),
                    depth - 1,
                    !isMaximizingPlayer,
                    evaluationResult.getCurrentAdvantage(),
                    newGameStatsAfterCurrMove.getCurrentPlayer()
            );

            double childEvaluationValue = childResult.getEvaluationValue();

            if (isMaximizingPlayer) {
                if (childEvaluationValue > maxValue) {
                    maxValue = childEvaluationValue;
                    bestMove = currMove;
                }
            } else {
                if (childEvaluationValue < minValue) {
                    minValue = childEvaluationValue;
                    bestMove = currMove;
                }
            }
        }

        return new MinimaxResult(bestMove, isMaximizingPlayer ? maxValue : minValue);
    }

}
