package com.example.Othello2.services;

import org.springframework.stereotype.Service;

@Service
public class InsideBoardCheckService {
    public Boolean check(Integer x, Integer y){
        return x >= 0 && x < 8 && y >= 0 && y <= 8;
    }
}
