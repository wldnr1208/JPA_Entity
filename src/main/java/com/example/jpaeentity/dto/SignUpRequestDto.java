package com.example.jpaeentity.dto;

import lombok.Getter;

//3
@Getter
public class SignUpRequestDto {

    private final String username;

    private final String password;

    private final Integer age;

    public SignUpRequestDto(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
