package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.example.Othello2.models.PlayerStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.Othello2.utils.*;
@Service
@AllArgsConstructor
public class CheckWinService {
    private static final FindValidMoveService findValidMoveService;
    private static Utils utils;
    public static Player checkWinOfPlayer(Cell[][] cells, Player currentPlayer){
            // so sánh số quân cờ
           Player opponentPlayer = utils.enemyOf(currentPlayer);
           int countPiecePlayer = 0,countPieceOppenents = 0;// khởi tạo số quân cờ
           for(int i = 0; i< cells.length; i++){
               for(int j = 0; j< cells.length; j++){
                   if(cells[i][j].getPiece().equals(currentPlayer)){ // đếm số quân cờ của người chơi hiện tại
                       countPiecePlayer ++;
                   }else{
                       countPieceOppenents++;// đếm số quân cờ của đối thủ
                   }
               }
           }
           if(countPiecePlayer + countPieceOppenents == 64){ // nếu full bàn cờ
               if(countPiecePlayer > countPieceOppenents){ // trả về người chơi nhiều quân cờ nhất
                   return currentPlayer;
               }else return opponentPlayer;
           }
            // kiểm tra nước đi hợp lệ
            List<Move> validMovesOfPlayer = findValidMoveService.findValidMoves(cells,currentPlayer);// đếm nước đi hợp lệ của người chơi hiện tại
            if(validMovesOfPlayer.size()<= 0){ // nếu người chơi hiện tại hết nước đi
                List<Move> validMovesOfOpponnents = findValidMoveService.findValidMoves(cells,opponentPlayer);// đếm đối thủ
                if(validMovesOfOpponnents.size() <= 0){// nếu đối thủ cx hết nước đi
                    if(countPiecePlayer >= countPieceOppenents) return currentPlayer;// so sánh số quân cờ
                    else return opponentPlayer;
                }
            }
        return null;
    }
}
