package com.joshua.service;

import com.joshua.domain.Board;
import com.joshua.dto.BoardDTO;
import com.joshua.repository.BoardRepository;
import com.joshua.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long saveBoard (Long memberId, BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        board.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 멤버가 없습니다.")));

        return boardRepository.save(board).getId();
    }

    public BoardDTO getBoard (Long id) {
        return new BoardDTO(findBoard(id));
    }

    public Long updateBoard (Long id, BoardDTO boardDTO) {
        Board board = findBoard(id);

        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContent(boardDTO.getBoardContent());
        return board.getId();
    }

    public void deleteBoard (Long id) {
        boardRepository.delete(findBoard(id));
    }

    public Page<BoardDTO> getAllBoards (Pageable pageable) {

        PageRequest id = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        Page<BoardDTO> boardList = boardRepository.findAll(id).map(BoardDTO::new);
        //Page<BoardDTO> boardList = boardRepository.findAll(pageable)
        //                .map(BoardDTO::new);
        return boardList;
    }

    public Board findBoard (Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시판 없습니다."));

    }
}
