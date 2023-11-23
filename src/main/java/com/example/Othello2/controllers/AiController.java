package com.example.Othello2.controllers;

import com.example.Othello2.AiServices.MinimaxService;
import com.example.Othello2.models.MinimaxResult;
import com.example.Othello2.models.request.MinimaxRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/othello/ai")
@AllArgsConstructor
@CrossOrigin
public class AiController {
    private final MinimaxService minimaxService;

    @PostMapping("/minimax")
    public MinimaxResult minimaxV1(@RequestBody MinimaxRequest request){
        return this.minimaxService.minimaxV1(request.getCells(), request.getDepth(), true, request.getEvaluationValue(), request.getCurrentPlayer());
    }
}
