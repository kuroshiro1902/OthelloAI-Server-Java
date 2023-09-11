package com.example.Othello2.services;

import com.example.Othello2.common.enums.Direction;
import com.example.Othello2.common.enums.Player;
import com.example.Othello2.models.Cell;
import com.example.Othello2.models.Move;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class IsValidMoveService {

    private final InsideBoardCheckService insideBoardCheckService;

    public Move isValidMove(Cell[][] cells, int row, int col, Player player) {

        Move validMove = null;

        // nếu ô đã có cờ
        if (!cells[row][col].getDisc().equals(Player.EMPTY)) {
            return null;
        }

        // duyệt qua các ô lân cận
        for (Direction direction : Direction.values()) {
            int adjacentX = row + direction.getX();
            int adjacentY = col + direction.getY();

            // nếu tọa độ x, y nằm trong bàn cờ
            if (insideBoardCheckService.check(adjacentX, adjacentY)) {

                Cell adjacentCell = cells[adjacentX][adjacentY];

                // những ô cờ bị lật
                List<Cell> flipCells = new ArrayList<>();

                // nếu ô cờ là của đối thủ
                if (Objects.nonNull(adjacentCell.getDisc()) &&
                        !Player.EMPTY.equals(adjacentCell.getDisc()) &&
                        !player.equals(adjacentCell.getDisc())){

                    boolean foundPlayerDisc = false;
                    flipCells.add(adjacentCell);
                    int x = adjacentX + direction.getX();
                    int y = adjacentY + direction.getY();

                    // Kiểm tra trên đường thẳng/ chéo nối từ ô hiện tại tới ô có quân cờ của người chơi hiện tại
                    while (insideBoardCheckService.check(x, y)) {
                        Cell currentCell = cells[x][y];
                        // Nếu ô hiện tại không có quân cờ
                        if (currentCell.getDisc().equals(Player.EMPTY)) {
                            break;
                        }
                        // Nếu ô hiện tại có quân cờ của người chơi hiện tại
                        if (currentCell.getDisc().equals(player)) {
                            foundPlayerDisc = true;
                            validMove = new Move(currentCell, cells[row][col], flipCells.toArray(new Cell[0]));
                            break; // Đã tìm thấy quân cờ của người hiện tại, nước đi hợp lệ
                        }
                        // Nếu ô hiện tại có quân cờ của đối thủ
                        flipCells.add(currentCell);
                        x += direction.getX();
                        y += direction.getY();
                    }
                    if (foundPlayerDisc) {
                        break;
                    }
                }
            }
        }

        return validMove;
    }
}