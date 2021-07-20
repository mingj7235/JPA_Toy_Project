package com.joshua.controller;

import com.joshua.domain.Board;
import com.joshua.dto.BoardDTO;
import com.joshua.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards/member/{memberId}")
    public Long saveBoard (@PathVariable Long memberId, BoardDTO boardDTO) {
        return boardService.saveBoard(memberId, boardDTO);
    }
//    @PostMapping("/boards")
//    public Long saveBoard (BoardDTO boardDTO) {
//        return boardService.saveBoard(boardDTO);
//    }

    @GetMapping("/boards/{id}")
    public BoardDTO getBoard (@PathVariable Long id) {
        return boardService.getBoard(id);
    }


    //size와 page는 param으로 날아가면 자동으로 pageable에 꽂힌다.
    @GetMapping("/boards")
    public Page<BoardDTO> getBoardList (Pageable pageable) {
        Page<BoardDTO> boardLists = boardService.getAllBoards(pageable);
        return boardLists;
    }


//    @GetMapping("/boards/{id}")
//    public Board getBoard (@PathVariable Long id) {
//        BoardDTO boardDTO = boardService.getBoard(id);
//        return
//    }

    @PutMapping ("/boards/{id}")
    public Long updateBoard (@PathVariable Long id, BoardDTO boardDTO) {
        return boardService.updateBoard(id, boardDTO);
    }

    @DeleteMapping ("/boards/{id}")
    public void deleteBoard (@PathVariable Long id) {
        boardService.deleteBoard(id);
    }


}
