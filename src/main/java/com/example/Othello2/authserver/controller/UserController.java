package com.example.Othello2.authserver.controller;

import com.example.Othello2.authserver.dto.user.TokenResponse;
import com.example.Othello2.authserver.dto.user.UserRequest;
import com.example.Othello2.authserver.entity.UserEntity;
import com.example.Othello2.authserver.service.UserService;
import com.example.Othello2.authserver.token.TokenHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Operation(summary = "Đăng ký tài khoản")
    @PostMapping("sign-up")
    public String signUp(@RequestBody UserRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

    @Operation(summary = "Đăng nhập")
    @PostMapping("log-in")
    public ResponseEntity logIn(@RequestBody @Valid UserRequest logInRequest){
        return new ResponseEntity(new TokenResponse(userService.logIn(logInRequest)), HttpStatus.OK);
    }

    @PostMapping
    public String genToken(@RequestBody UserEntity userEntity){
        return TokenHandler.generateToken(userEntity);
    }
}
