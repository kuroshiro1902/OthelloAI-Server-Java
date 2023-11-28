package com.example.Othello2.authserver.service;

import com.example.Othello2.authserver.dto.user.UserRequest;
import com.example.Othello2.authserver.entity.DifficultEntity;
import com.example.Othello2.authserver.entity.UserEntity;
import com.example.Othello2.authserver.mapper.UserMapper;
import com.example.Othello2.authserver.repository.DifficultRepository;
import com.example.Othello2.authserver.repository.UserRepository;
import com.example.Othello2.authserver.token.TokenHandler;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DifficultRepository difficultRepository;

    public String signUp(UserRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new RuntimeException("Username already exists!!!");
        }
        signUpRequest.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt()));
        UserEntity userEntity = UserMapper.getEntityFromRequest(signUpRequest);
        userRepository.save(userEntity);

        List<DifficultEntity> difficultEntities = new ArrayList<>();
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(1)
                        .userId(userEntity.getId())
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(3)
                        .userId(userEntity.getId())
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(5)
                        .userId(userEntity.getId())
                        .build()
        );
        difficultEntities.add(
                DifficultEntity.builder()
                        .depth(7)
                        .userId(userEntity.getId())
                        .build()
        );
        difficultRepository.saveAll(difficultEntities);

        return TokenHandler.generateToken(userEntity);
    }

    public String logIn(UserRequest logInRequest){
        UserEntity userEntity = userRepository.findByUsername(logInRequest.getUsername());
        if (Objects.isNull(userEntity)){
            throw new RuntimeException("Incorrect password!!!");
        }
        String currentHashedPassword = userEntity.getPassword();
        if (BCrypt.checkpw(logInRequest.getPassword(), currentHashedPassword)){
            return TokenHandler.generateToken(userEntity);
        }
        throw new RuntimeException("Incorrect password!!!");
    }

}
