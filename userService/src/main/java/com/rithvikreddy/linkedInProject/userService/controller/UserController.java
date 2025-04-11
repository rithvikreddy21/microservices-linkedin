package com.rithvikreddy.linkedInProject.userService.controller;

import com.rithvikreddy.linkedInProject.userService.dto.LoginRequestDto;
import com.rithvikreddy.linkedInProject.userService.dto.SignupRequestDto;
import com.rithvikreddy.linkedInProject.userService.dto.UserDto;
import com.rithvikreddy.linkedInProject.userService.entity.User;
import com.rithvikreddy.linkedInProject.userService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        UserDto userdto = authService.signUp(signupRequestDto);
        return new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String token = authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
