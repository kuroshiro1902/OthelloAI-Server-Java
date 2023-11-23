package com.example.Othello2.GameServices;

import com.example.Othello2.GameServices.helpers.FindWinnerService;
import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.EvaluationRes;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;
import com.example.Othello2.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class DynamicEvaluationService {

    private final FindValidMoveService findValidMoveService;
    private final FindWinnerService findWinnerService;
    private final Utils utils;

    public EvaluationRes dynamicEvaluation(Cell[][] cells, Player currentPlayer, List<Move> validMovesForCurrentPlayer) {
        Player opponentPlayer = utils.enemyOf(currentPlayer);
        PlayerStats currentPlayerStats = new PlayerStats(currentPlayer);
        PlayerStats opponentPlayerStats = new PlayerStats(opponentPlayer);

        //count pieces
        for(Cell[] row: cells){
            for(Cell cell: row){
                String _edgeOrCorner = this.isEdgeOrCorner(cell);
                //current Player
                if(cell.getPiece() == currentPlayer){
                    currentPlayerStats.getPieces().add(cell);

                    assert _edgeOrCorner != null;
                    if(_edgeOrCorner.equals("corner")){
                        currentPlayerStats.setCornerPiecesAmount(currentPlayerStats.getCornerPiecesAmount() + 1);
                    }
                    else if(_edgeOrCorner.equals("edge")){
                        currentPlayerStats.setEdgePiecesAmount(currentPlayerStats.getEdgePiecesAmount() + 1);
                    }

                }
                //opponent Player
                else{
                    opponentPlayerStats.getPieces().add(cell);
                    if(_edgeOrCorner.equals("corner")){
                        opponentPlayerStats.setCornerPiecesAmount(opponentPlayerStats.getCornerPiecesAmount() + 1);
                    }
                    else if(_edgeOrCorner.equals("edge")){
                        opponentPlayerStats.setEdgePiecesAmount(opponentPlayerStats.getEdgePiecesAmount() + 1);
                    }
                }
            }
        }

        //count valid moves
        if(validMovesForCurrentPlayer != null){
            currentPlayerStats.setValidMovesAmount(validMovesForCurrentPlayer.size());
        }
        else{
            currentPlayerStats.setValidMovesAmount(findValidMoveService.findValidMoves(cells, currentPlayer).size());
        }
        opponentPlayerStats.setValidMovesAmount(findValidMoveService.findValidMoves(cells, opponentPlayer).size());

        //check win
        Player winner = findWinnerService.findWinner(currentPlayerStats, opponentPlayerStats);
        double winValue = Math.pow(10,7);
        if(winner.equals(currentPlayerStats.getPlayer())){
            currentPlayerStats.setEvaluationValue(winValue);
            opponentPlayerStats.setEvaluationValue(-winValue);
        }
        else{
            currentPlayerStats.setEvaluationValue(-winValue);
            opponentPlayerStats.setEvaluationValue(winValue);
        }

        //evaluate
        currentPlayerStats.evaluate();
        opponentPlayerStats.evaluate();
        return new EvaluationRes(currentPlayerStats, opponentPlayerStats, currentPlayerStats.getEvaluationValue() - opponentPlayerStats.getEvaluationValue(), winner);
    }

    private String isEdgeOrCorner(Cell cell){ //return: "edge | "corner" | null
        int x = cell.getX();
        int y = cell.getY();
        boolean isXEdge = (x == 0 || x == 8 - 1); //8: Độ lớn của bảng
        boolean isYEdge = (y == 0 || y == 8 - 1);
        if(isXEdge && isYEdge){
            return "corner";
        }
        if(isXEdge || isYEdge){
             return "edge";
        }
        return "none";
    }
}
