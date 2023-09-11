package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;

public class CountEdgeDiscOfPlayerService {
    public static int countEdgeDiscsOfPlayer(Cell[][] cells, Player player) {
        int n = cells.length;
        int counter = 0;
        int[] edges = {0, n - 1}; // Rows and columns at positions 0 and n-1 are edges

        for (int edge : edges) {
            // Rows 0 and n-1
            for (int col = 1; col < n - 1; col++) {
                // 1 -> n-1: Exclude corner cells
                if (cells[edge][col].getDisc() == player) {
                    counter++;
                }
            }

            // Columns 0 and n-1
            for (int row = 1; row < n - 1; row++) {
                if (cells[row][edge].getDisc() == player) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
