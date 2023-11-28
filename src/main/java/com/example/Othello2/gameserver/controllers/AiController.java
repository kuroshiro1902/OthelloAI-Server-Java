package com.example.Othello2.gameserver.controllers;

import com.example.Othello2.AiServices.MinimaxService;
import com.example.Othello2.gameserver.models.MinimaxResult;
import com.example.Othello2.gameserver.models.request.MinimaxRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/othello/ai")
@AllArgsConstructor
@CrossOrigin
public class AiController {
    private final MinimaxService minimaxService;

    @PostMapping("/minimax/v2")
    public MinimaxResult minimaxV2(@RequestBody MinimaxRequest request){
        return this.minimaxService.minimaxV2(request.getCells(), request.getDepth(), true, request.getEvaluationValue(), request.getCurrentPlayer(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, new PositionCount(0));
    }
    @PostMapping("/minimax/v1")
    public MinimaxResult minimaxV1(@RequestBody MinimaxRequest request){
        return this.minimaxService.minimaxV1(request.getCells(), request.getDepth(), true, request.getEvaluationValue(), request.getCurrentPlayer());
    }
}
