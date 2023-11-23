package com.example.Othello2.models;

import com.example.Othello2.GameServices.helpers.HeuristicValueArray;
import com.example.Othello2.common.enums.EvaluationWeight;
import com.example.Othello2.common.enums.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PlayerStats {
    private Player player;
    private List<Cell> pieces = new ArrayList<>();
    private int edgePiecesAmount = 0;
    private int cornerPiecesAmount = 0;
    private int validMovesAmount = 0;
    private double evaluationValue = 0;

    public PlayerStats(Player player){
        this.player = player;
    }
    public PlayerStats(Player player, List<Cell> pieces, int edgePiecesAmount, int cornerPiecesAmount, int validMovesAmount){
        this.player = player;
        this.pieces = pieces;
        this.edgePiecesAmount = edgePiecesAmount;
        this.cornerPiecesAmount = cornerPiecesAmount;
        this.validMovesAmount = validMovesAmount;
    }
    public void evaluate(){
        double positionValue = 0;
        for(Cell cell: pieces){
            positionValue += HeuristicValueArray.board[cell.getX()][cell.getY()];
        }
        double pieceAmountValue = this.pieces.size() * EvaluationWeight.PIECE_AMOUNT.getValue();
        double edgePieceValue = this.edgePiecesAmount * EvaluationWeight.EDGE_PIECES_AMOUNT.getValue();
        double cornerPieceValue = this.cornerPiecesAmount * EvaluationWeight.CORNER_PIECES_AMOUNT.getValue();
        double validMoveValue = this.validMovesAmount * EvaluationWeight.VALID_MOVES_AMOUNT.getValue();
        this.evaluationValue = positionValue + pieceAmountValue + edgePieceValue + cornerPieceValue + validMoveValue;
    }
}
