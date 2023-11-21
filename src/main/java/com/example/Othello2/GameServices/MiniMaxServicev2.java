package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.EvaluationRes;
import com.example.Othello2.models.MinimaxResult;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.response.GameStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MiniMaxServicev2 {
    private final FindValidMoveService findvalidmoveService;
    private final MoveService moveService;
    private final DynamicEvaluationService dynamicEvaluationService;

    public MinimaxResult minimaxv2(Cell[][] cells,
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
        if (isMaximizingPlayer) {
            double maxValue = Integer.MIN_VALUE;
            Move bestMove = null;

            for (Move validMove : validMoves) {
                currMove = validMove;
                GameStats newGameStatsAfterCurrMove = moveService.move(cells, player, currMove);

                // Dynamic evaluation and other necessary processing
                EvaluationRes evaluationResult = dynamicEvaluationService.dynamicEvaluation(
                        newGameStatsAfterCurrMove.getCells(),
                        newGameStatsAfterCurrMove.getCurrentPlayer(),
                        newGameStatsAfterCurrMove.getValidMoves());

                // Gọi đệ quy với Minimax
                MinimaxResult childResult = minimaxv2(
                        newGameStatsAfterCurrMove.getCells(),
                        depth - 1,
                        !isMaximizingPlayer,
                        evaluationResult.getCurrentAdvantage(),
                        newGameStatsAfterCurrMove.getCurrentPlayer()
                );

                double childEvaluationValue = childResult.getEvaluationValue();
                if (childEvaluationValue > maxValue) {
                    maxValue = childEvaluationValue;
                    bestMove = currMove;
                }
                return new MinimaxResult(bestMove, maxValue);
            }
        } else {
            double minValue = Integer.MAX_VALUE;
            Move bestMove = null;

            for (Move validMove : validMoves) {
                currMove = validMove;
                GameStats newGameStatsAfterCurrMove = moveService.move(cells, player, currMove);

                // Dynamic evaluation and other necessary processing
                EvaluationRes evaluationResult = dynamicEvaluationService.dynamicEvaluation(
                        newGameStatsAfterCurrMove.getCells(),
                        newGameStatsAfterCurrMove.getCurrentPlayer(),
                        newGameStatsAfterCurrMove.getValidMoves());

                // Gọi đệ quy với Minimax
                MinimaxResult childResult = minimaxv2(
                        newGameStatsAfterCurrMove.getCells(),
                        depth - 1,
                        isMaximizingPlayer,
                        evaluationResult.getCurrentAdvantage(),
                        newGameStatsAfterCurrMove.getCurrentPlayer()
                );

                double childEvaluationValue = childResult.getEvaluationValue();
                if (childEvaluationValue < minValue) {
                    minValue = childEvaluationValue;
                    bestMove = currMove;
                }
                return new MinimaxResult(bestMove, minValue);
            }
        }
        return null;
    }
}
