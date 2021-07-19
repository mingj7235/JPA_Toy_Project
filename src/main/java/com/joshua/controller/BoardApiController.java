package com.joshua.controller;

import com.joshua.dto.BoardDTO;
import com.joshua.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board")
    public void saveBoard (BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);
    }
}
