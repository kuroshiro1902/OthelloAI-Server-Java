package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;

import java.util.ArrayList;
import java.util.List;

public class FindDiscOfPlayerService {
    public static List<Cell> findDiscsOfPlayer(Cell[][] cells, Player player) {
        List<Cell> discs = new ArrayList<>();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getDisc() == player) {
                    discs.add(cell);
                }
            }
        }

        return discs;
    }
}
