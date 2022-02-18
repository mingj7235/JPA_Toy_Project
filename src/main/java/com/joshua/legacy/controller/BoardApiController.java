package com.joshua.legacy.controller;

import com.joshua.legacy.dto.BoardDTO;
import com.joshua.legacy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards/member/{memberId}")
    public String saveBoard (@PathVariable Long memberId, BoardDTO boardDTO) {
        Long boardId = boardService.saveBoard(memberId, boardDTO);
        return "redirect:/boards/"+boardId;
    }
//    @PostMapping("/boards")
//    public Long saveBoard (BoardDTO boardDTO) {
//        return boardService.saveBoard(boardDTO);
//    }

    @GetMapping("/boards/{id}")
    @ResponseBody
    public BoardDTO getBoard (@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    //size와 page는 param으로 날아가면 자동으로 pageable에 꽂힌다.
    @GetMapping("/boards")
    @ResponseBody
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
    public String updateBoard (@PathVariable Long id, BoardDTO boardDTO) {
        Long boardId = boardService.updateBoard(id, boardDTO);
        return "redirect:/boards/" + boardId;
    }

    @DeleteMapping ("/boards/{id}")
    public String deleteBoard (@PathVariable Long id) {
        Long boardId = boardService.findBoard(id).getId();
        boardService.deleteBoard(id);
        return "redirect:/boards/" + boardId;
    }

}
