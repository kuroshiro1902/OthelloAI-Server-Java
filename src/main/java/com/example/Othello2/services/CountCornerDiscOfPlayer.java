package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;

public class CountCornerDiscOfPlayer {
    public static int countCornerDiscsOfPlayer(Cell[][] cells, Player player) {
        int n = cells.length;
        int counter = 0;
        int[] corners = {0, n - 1};

        for (int row : corners) {
            for (int col : corners) {
                if (cells[row][col].getDisc() == player) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
