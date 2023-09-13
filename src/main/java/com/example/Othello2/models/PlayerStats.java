package com.example.Othello2.models;

import com.example.Othello2.common.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class PlayerStats {
    private Player player;
    private List<Cell> discs;
    private int edgeDiscsAmount;
    private int cornerDiscsAmount;
    private int validMovesAmount;
}
