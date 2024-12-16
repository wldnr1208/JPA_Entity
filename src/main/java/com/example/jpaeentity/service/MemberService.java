package com.example.jpaeentity.service;

import com.example.jpaeentity.dto.MemberResponseDto;
import com.example.jpaeentity.dto.SignUpResponseDto;
import com.example.jpaeentity.entity.Member;
import com.example.jpaeentity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//6
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, Integer age) {

        Member member = new Member(username, password, age);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getAge());
    }

    public MemberResponseDto findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not id found : "+id);
        }

        Member findMember = optionalMember.get();
        return new MemberResponseDto(findMember.getUsername(), findMember.getAge());
    }

    @Transactional
    public void updatPassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByOrElseThrow(id);

       if(!findMember.getPassword().equals(oldPassword)){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
       };

       findMember.updatePassword(newPassword);
    }
}
