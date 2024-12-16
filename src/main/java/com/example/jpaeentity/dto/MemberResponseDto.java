package com.example.jpaeentity.dto;

import lombok.Getter;

//8 특정회원 조회기능 유저네임과 나이만
@Getter
public class MemberResponseDto {

    private final String username;

    private final Integer age;

    public MemberResponseDto(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
