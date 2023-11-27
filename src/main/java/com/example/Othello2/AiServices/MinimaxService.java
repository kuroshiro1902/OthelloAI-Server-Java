package com.example.Othello2.AiServices;

import com.example.Othello2.gameserver.GameServices.DynamicEvaluationService;
import com.example.Othello2.gameserver.GameServices.FindValidMoveService;
import com.example.Othello2.gameserver.GameServices.MoveService;
import com.example.Othello2.gameserver.enums.Player;
import com.example.Othello2.gameserver.models.Cell;
import com.example.Othello2.gameserver.models.EvaluationRes;
import com.example.Othello2.gameserver.models.MinimaxResult;
import com.example.Othello2.gameserver.models.Move;
import com.example.Othello2.gameserver.models.response.GameStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class MinimaxService {
    private final FindValidMoveService findValidMoveService;
    private final MoveService moveService;
    private DynamicEvaluationService dynamicEvaluationService;
    public MinimaxResult minimaxV1(Cell[][] cells, int depth, boolean isMaximizingPlayer, double evaluationValue, Player player){
        //generate game tree
        List<Move> validMoves = this.findValidMoveService.findValidMoves(cells, player);
        //sort moves randomly
        Collections.shuffle(validMoves);

        Move currMove;

        // Maximum depth exceeded or node is a terminal node (no children)
        if(depth == 0 || validMoves.size() == 0){
            return new MinimaxResult(null, evaluationValue);
        }

        /*
        Find maximum/minimum from list of validMoves
        */
        double maxValue = Double.NEGATIVE_INFINITY;
        double minValue = Double.POSITIVE_INFINITY;
        Move bestMove = null;

        for (Move validMove : validMoves) {
            currMove = validMove;

            //move currMove
            GameStats newGameStatsAfterCurrMove = moveService.move(cells.clone(), player, currMove);

            //evaluate currMove
            EvaluationRes evaluationRes = this.dynamicEvaluationService.dynamicEvaluation(newGameStatsAfterCurrMove.getCells(), newGameStatsAfterCurrMove.getCurrentPlayer(), newGameStatsAfterCurrMove.getValidMoves());
            double newEvaluationValue = evaluationRes.getCurrentAdvantage();

            //minimax recursion
            MinimaxResult childMinimaxResult = this.minimaxV1(this.clone2DArray(newGameStatsAfterCurrMove.getCells()), depth-1, !isMaximizingPlayer, newEvaluationValue, newGameStatsAfterCurrMove.getCurrentPlayer() );
            double childEvaluationValue = childMinimaxResult.getEvaluationValue();
            if(isMaximizingPlayer){
                if(childEvaluationValue > maxValue){
                    maxValue = childEvaluationValue;
                    bestMove = currMove;
                }
                /*cut off Alpha-beta
                ...
                */
            }
            else{
                if(childEvaluationValue<minValue){
                    minValue = childEvaluationValue;
                    bestMove = currMove;
                }
                /*cut off Alpha-beta
                ...
                */
            }
        }

        //return
        if(isMaximizingPlayer){
            return new MinimaxResult(bestMove, maxValue);
        }
        else{
            return new MinimaxResult(bestMove, minValue);
        }
    }
    public Cell[][] clone2DArray(Cell[][] original) {
        int length = original.length;
        Cell[][] copy = new Cell[length][];
        for (int i = 0; i < length; i++) {
            Cell[] aMatrix = original[i];
            int aLength = aMatrix.length;
            copy[i] = new Cell[aLength];
            System.arraycopy(aMatrix, 0, copy[i], 0, aLength);
        }
        return copy;
    }
}
