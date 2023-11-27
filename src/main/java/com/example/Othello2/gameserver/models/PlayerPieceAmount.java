package com.example.Othello2.gameserver.models;

import com.example.Othello2.gameserver.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerPieceAmount {
    private int whiteAmount = 0;
    private int blackAmount = 0;

    public PlayerPieceAmount(Cell[][] cells){

        for(Cell[] row: cells){
            for(Cell cell: row){
                if(cell.getPiece() == Player.WHITE) this.whiteAmount++;
                if(cell.getPiece() == Player.BLACK) this.blackAmount++;
            }
        }
    }
    public int getAmountByPlayer(Player player){
        if(player == Player.BLACK) return this.blackAmount;
        if(player == Player.WHITE) return this.whiteAmount;
        return 64 - this.whiteAmount - this.blackAmount;
    }
}
