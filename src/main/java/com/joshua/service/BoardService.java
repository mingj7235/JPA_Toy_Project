package com.joshua.service;

import com.joshua.domain.Board;
import com.joshua.dto.BoardDTO;
import com.joshua.repository.BoardRepository;
import com.joshua.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long saveBoard (BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        board.setMember(memberRepository.findById(boardDTO.getMember_id())
                .orElseThrow(() -> new IllegalArgumentException("찾는 멤버가 없습니다.")));

        return boardRepository.save(board).getId();
    }

    public BoardDTO getBoard (Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시판 없슴"));
        return new BoardDTO(board);
    }
}
