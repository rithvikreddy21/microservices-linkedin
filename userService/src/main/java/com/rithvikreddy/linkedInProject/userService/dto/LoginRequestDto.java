package com.rithvikreddy.linkedInProject.userService.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
