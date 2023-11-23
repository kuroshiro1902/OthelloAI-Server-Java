package com.example.Othello2.GameServices.helpers;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.PlayerStats;
import org.springframework.stereotype.Service;

@Service
public class FindWinnerService {
    public Player findWinner(PlayerStats currentPlayerStats, PlayerStats opponentPlayerStats){
        //Nếu bàn cờ không còn ô trống
        if(currentPlayerStats.getPieces().size() + opponentPlayerStats.getPieces().size() == 64){
            return this.winner(currentPlayerStats, opponentPlayerStats);
        }
        //Nếu cả 2 không còn nước đi hợp lệ
        if(currentPlayerStats.getValidMovesAmount() == 0 && opponentPlayerStats.getValidMovesAmount()==0) {
            return this.winner(currentPlayerStats, opponentPlayerStats);
        }
            return null;
    }
    private Player winner(PlayerStats currentPlayerStats, PlayerStats opponentPlayerStats){
        if(currentPlayerStats.getPieces().size() >  opponentPlayerStats.getPieces().size()){
            return currentPlayerStats.getPlayer();
        }
        else if(currentPlayerStats.getPieces().size() <  opponentPlayerStats.getPieces().size()){
            return opponentPlayerStats.getPlayer();
        }
        else return null;
    }
}
