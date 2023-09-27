package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;

public class CountCornerPieceOfPlayer {
    public static int countCornerPiecesOfPlayer(Cell[][] cells, Player player) {
        int n = cells.length; // = 8
        int counter = 0;
        int[] corners = {0, n - 1};

        for (int row : corners) {
            for (int col : corners) {
                if (cells[row][col].getPiece().equals(player)) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
