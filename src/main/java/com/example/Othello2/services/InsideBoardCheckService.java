package com.example.Othello2.services;

import org.springframework.stereotype.Service;

@Service
public class InsideBoardCheckService {
    public Boolean check(Integer x, Integer y, Integer size){
        return x >= 0 && x < size && y >= 0 && y <= size;
    }
}
