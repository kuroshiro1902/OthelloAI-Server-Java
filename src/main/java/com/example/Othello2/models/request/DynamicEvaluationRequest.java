package com.example.Othello2.models.request;

import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DynamicEvaluationRequest {
    private Cell[][] cells;
    private String player;
    private List<Move> validMovesForPlayer;
}
