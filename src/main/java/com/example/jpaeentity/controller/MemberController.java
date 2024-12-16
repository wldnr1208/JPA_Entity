//5
package com.example.jpaeentity.controller;

import com.example.jpaeentity.dto.MemberResponseDto;
import com.example.jpaeentity.dto.SignUpRequestDto;
import com.example.jpaeentity.dto.SignUpResponseDto;
import com.example.jpaeentity.dto.UpdatePasswordRequestDto;
import com.example.jpaeentity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
//생성자 주입받기 위해
@RequiredArgsConstructor
public class MemberController {
    
    //이부분 작성하면 생성자 생성시 패키지는 서비스로
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto>signUp(@RequestBody SignUpRequestDto requestDto) {

        //ctrl+alt+v
        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto.getUsername(), requestDto.getPassword(), requestDto.getAge());


        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto>findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void>updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto) {
        memberService.updatPassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
