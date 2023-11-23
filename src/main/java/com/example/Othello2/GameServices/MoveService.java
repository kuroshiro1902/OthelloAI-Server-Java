package com.example.Othello2.GameServices;

import com.example.Othello2.GameServices.helpers.FindWinnerService;
import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerPieceAmount;
import com.example.Othello2.models.response.GameStats;
import com.example.Othello2.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MoveService {
    private final Utils utils;
    private final FindValidMoveService findValidMoveService;
    private final DynamicEvaluationService dynamicEvaluationService;
    private final FindWinnerService findWinnerService;

    //nếu trả về newCurrentPlayer = Player.Both => Hòa.
    //nếu trả về evaluationValue = 10^7 => newCurrentPlayer Thắng.
    //nếu trả về evaluationValue = -10^7 => currentPlayer Thắng.
    public GameStats move(Cell[][] cells, Player currentPlayer, Move move){
        Player lastPlayer = currentPlayer;
        Player newCurrentPlayer;

        //Đổi lượt, lật quân cờ.
        if(move != null){
            newCurrentPlayer = utils.enemyOf(lastPlayer);
            cells[move.getTo().getX()][move.getTo().getY()].setPiece(lastPlayer);
            for(Cell cell: move.getFlips()){
                cells[cell.getX()][cell.getY()].setPiece(lastPlayer);
            }
        }
        else{
            newCurrentPlayer = lastPlayer;
        }

        //Tìm nước đi hợp lệ mới sau khi đi một nước
        List<Move> newValidMoves = findValidMoveService.findValidMoves(cells, newCurrentPlayer);

        //Nếu người chơi tiếp theo không có nước đi hợp lệ thì đổi lượt chơi cho người trước đó
        if(newValidMoves.size() == 0){
            newCurrentPlayer = lastPlayer;
            newValidMoves = findValidMoveService.findValidMoves(cells, lastPlayer);
        }

        return new GameStats(cells, newCurrentPlayer, newValidMoves, this.dynamicEvaluationService.dynamicEvaluation(cells, newCurrentPlayer, newValidMoves).getCurrentAdvantage());
    }
}
