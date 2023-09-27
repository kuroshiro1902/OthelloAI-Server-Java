package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPieceService {
    public List<Cell> getAllPiecesOfThisPlayer(Cell[][] cells, Player player){
        List<Cell> result = new ArrayList<>();
        for ( Cell[] row : cells ){
            for (Cell cell : row){
                if (player == cell.getPiece()){
                    result.add(cell);
                }
            }
        }
        return result;
    }
}
