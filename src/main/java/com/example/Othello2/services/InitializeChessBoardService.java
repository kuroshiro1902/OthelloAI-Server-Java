package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import org.springframework.stereotype.Service;

@Service
public class InitializeChessBoardService {
    private static final Integer size = 8;
    public Cell[][] initialize(){
        Cell[][] cells = new Cell[size][size];
        int centerIndex = size/2 - 1;

        for (int x = 0 ; x < size; x++){
            for (int y = 0 ; y < size; y++){
                cells[x][y] = new Cell(x, y, Player.EMPTY);
            }
        }

        cells[centerIndex][centerIndex].setPiece(Player.WHITE);
        cells[centerIndex][centerIndex + 1].setPiece(Player.BLACK);
        cells[centerIndex + 1][centerIndex].setPiece(Player.BLACK);
        cells[centerIndex + 1][centerIndex + 1].setPiece(Player.WHITE);

        return cells;
    }
}
