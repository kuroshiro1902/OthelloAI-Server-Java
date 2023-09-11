package com.example.Othello2.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Direction {
    TOP_LEFT(-1, -1),
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM(1, 0),
    BOTTOM_RIGHT(1, 1);

    private final int x;
    private final int y;
}
