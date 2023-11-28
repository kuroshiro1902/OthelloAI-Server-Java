package com.example.Othello2.gameserver.GameServices.helpers;

import org.springframework.stereotype.Service;

@Service
public class IsInsideBoardService {
    public Boolean check(Integer x, Integer y){
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
