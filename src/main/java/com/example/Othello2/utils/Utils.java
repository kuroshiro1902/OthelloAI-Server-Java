package com.example.Othello2.utils;

import com.example.Othello2.common.enums.Player;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    public Player enemyOf(Player player){
        if(player.equals(Player.BLACK)) return Player.WHITE;
        return Player.BLACK;
    }
    public Utils(){

    }
}
