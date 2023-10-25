package com.example.Othello2.models;
import com.example.Othello2.GameServices.HeuristicValueArray;
import com.example.Othello2.common.enums.EvaluationWeight;
import com.example.Othello2.common.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.example.Othello2.common.enums.EvaluationWeight.*;

@AllArgsConstructor
@Getter
@Setter

public class PlayerStats {
    public Player player;
    public Cell[][] pieces;
    public int edgePiecesAmount;
    public int cornerPiecesAmount;
    public int validMovesAmount;
    public double evaluationValue;

    public void evaluate(){
        double positionValue = 0;
        Integer[][] heuristicValueArray = HeuristicValueArray.heuristicValueArray();
        for (Cell cell : pieces) {
            positionValue += heuristicValueArray[cell.getX()][cell.getY()];
        }

        // piece ratio
        // code here...

        double pieceAmountValue = (double) (this.pieces.size() * EvaluationWeight.PIECE_AMOUNT.getValue());
        double edgePieceValue = (double) (this.edgePiecesAmount * EDGE_PIECES_AMOUNT.getValue());
        double cornerPieceValue = (double) (this.cornerPiecesAmount * CORNER_PIECES_AMOUNT.getValue());
        double validMoveValue = (double) (this.validMovesAmount * VALID_MOVES_AMOUNT.getValue());
        this.evaluationValue = positionValue + pieceAmountValue + edgePieceValue + cornerPieceValue + validMoveValue;

    }
}
