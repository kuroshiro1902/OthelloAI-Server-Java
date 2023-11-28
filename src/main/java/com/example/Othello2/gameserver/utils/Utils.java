package com.example.Othello2.gameserver.utils;

import com.example.Othello2.gameserver.enums.Player;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    public Utils(){
    }
    public Player enemyOf(Player player){
        if(player.equals(Player.BLACK)) return Player.WHITE;
        return Player.BLACK;
    }
}
