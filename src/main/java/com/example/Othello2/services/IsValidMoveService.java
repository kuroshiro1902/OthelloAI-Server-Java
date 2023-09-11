package com.example.Othello2.services;

import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IsValidMoveService {
    public Move isValidMove(Cell[][] cells, int row, int col, Player player){
        if (Objects.nonNull(cells[row][col].getDisc())){

        }
        return null;
    }
}
