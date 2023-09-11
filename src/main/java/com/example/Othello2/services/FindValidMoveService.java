package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;

import java.util.ArrayList;
import java.util.List;
import com.example.Othello2.services.IsValidMoveService;
public class FindValidMoveService {
    public static List<Move> findValidMoves(Cell[][] cells, Player player) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Move move = isValidMove(cells, i, j, player);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }
}
