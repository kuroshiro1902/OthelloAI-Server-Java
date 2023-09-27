package com.example.Othello2.models.request;

import com.example.Othello2.models.Cell;
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
public class IsValidMovesRequest {
    private Integer row;
    private Integer col;
    private String player;
    private Cell[][] cells;
}
