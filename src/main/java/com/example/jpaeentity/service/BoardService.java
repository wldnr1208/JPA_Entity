package com.example.jpaeentity.service;

import com.example.jpaeentity.dto.BoardResponseDto;
import com.example.jpaeentity.dto.BoardWithAgeResponseDto;
import com.example.jpaeentity.entity.Board;
import com.example.jpaeentity.entity.Member;
import com.example.jpaeentity.repository.BoardRepository;
import com.example.jpaeentity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    public BoardResponseDto save(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents);
        board.setMember(findMember);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents());
    };

    public List<BoardResponseDto> findAll() {

        boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::toDto)
                .toList();
        return null;
    }

    public BoardWithAgeResponseDto findById(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        Member writer = findBoard.getMember();

        return new BoardWithAgeResponseDto(findBoard.getContents(), findBoard.getTitle(), writer.getAge());
    }

    public void delete(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(findBoard);
    }
}
