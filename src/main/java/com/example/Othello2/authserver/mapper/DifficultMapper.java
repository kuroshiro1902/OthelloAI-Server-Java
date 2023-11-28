package com.example.Othello2.authserver.mapper;

import com.example.Othello2.authserver.dto.difficult.DifficultRequest;
import com.example.Othello2.authserver.dto.difficult.DifficultResponse;
import com.example.Othello2.authserver.entity.DifficultEntity;

public class DifficultMapper {
    public static DifficultEntity getEntityFromInput(DifficultRequest request){
        return new DifficultEntity(null, request.getAmount(), null);
    }

    public static DifficultResponse getResponseFromEntity(DifficultEntity entity){
        return new DifficultResponse(entity.getId(), entity.getAmount());
    }
}
