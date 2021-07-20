package com.joshua.controller;

import com.joshua.dto.BoardDTO;
import com.joshua.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public void saveBoard (BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);
    }

    @GetMapping("/boards/{id}")
    public BoardDTO getBoard (@PathVariable Long id) {
        return boardService.getBoard(id);
    }
}
