package com.example.jpaeentity.controller;

import com.example.jpaeentity.dto.BoardResponseDto;
import com.example.jpaeentity.dto.BoardWithAgeResponseDto;
import com.example.jpaeentity.dto.CreateBoardRequestDto;
import com.example.jpaeentity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PatchMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto){

        BoardResponseDto boardResponsDto = boardService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(boardResponsDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll(){
        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardWithAgeResponseDto> findById(@PathVariable Long id){

        boardService.findById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
