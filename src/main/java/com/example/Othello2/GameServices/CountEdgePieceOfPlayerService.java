package com.example.Othello2.GameServices;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;

public class CountEdgePieceOfPlayerService {
    public static int countEdgePiecesOfPlayer(Cell[][] cells, Player player) {
        int n = cells.length;
        int counter = 0;
        int[] edges = {0, n - 1}; // Rows and columns at positions 0 and n-1 are edges
        // 0 7

        for (int edge : edges) {
            // Rows 0 and n-1
            for (int col = 1; col < n - 1; col++) {
                // 1 -> n-1: Exclude corner cells
                if (cells[edge][col].getPiece().equals(player)) {
                    counter++;
                }
            }

            // Columns 0 and n-1
            for (int row = 1; row < n - 1; row++) {
                if (cells[row][edge].getPiece().equals(player)) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
