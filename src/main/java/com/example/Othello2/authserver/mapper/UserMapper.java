package com.example.Othello2.authserver.mapper;


import com.example.Othello2.authserver.dto.user.UserRequest;
import com.example.Othello2.authserver.entity.UserEntity;

public class UserMapper {
    public static UserEntity getEntityFromRequest(UserRequest signUpRequest){
        return UserEntity.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .build();
    }
}
