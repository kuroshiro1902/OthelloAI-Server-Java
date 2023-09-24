package com.example.Othello2.models.response;

import com.example.Othello2.common.enums.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class PlayerStats {
    private Player player;
    private boolean isTurn;
    private int pieceCount;
    private int cornerPieceCount;
    private int edgePieceCount;
    private int dangerPieceCount;
    private boolean hasAvailableMoves;
}
