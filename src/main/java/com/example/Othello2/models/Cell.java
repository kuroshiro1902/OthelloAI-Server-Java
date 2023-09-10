package com.example.Othello2.models;

import com.example.Othello2.common.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cell {
    private int x;
    private int y;
    private Player disc;

    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }
}
