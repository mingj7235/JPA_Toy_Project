package com.joshua.controller;

import com.joshua.domain.Board;
import com.joshua.dto.BoardDTO;
import com.joshua.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public Long saveBoard (BoardDTO boardDTO) {
        return boardService.saveBoard(boardDTO);
    }

    @GetMapping("/boards/{id}")
    public BoardDTO getBoard (@PathVariable Long id) {
        return boardService.getBoard(id);
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
