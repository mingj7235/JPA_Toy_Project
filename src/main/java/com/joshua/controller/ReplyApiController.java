package com.joshua.controller;

import com.joshua.dto.ReplyDTO;
import com.joshua.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping ("/replies/boards/{boardId}/members/{memberId}")
    public String saveReply (@PathVariable Long boardId, @PathVariable Long memberId, ReplyDTO replyDTO) {
//        return replyService.saveReply(boardId, memberId, replyDTO);
        replyService.saveReply(boardId, memberId, replyDTO);
        return "redirect:/boards/"+boardId;

    }

    @GetMapping ("/replies/{id}")
    @ResponseBody
    public ReplyDTO getReply (@PathVariable Long id) {
        return replyService.getReply(id);
    }

    //json 확인용
    @GetMapping ("/replies")
    @ResponseBody
    public Page<ReplyDTO> getAllReplies (Pageable pageable) {
        return replyService.getAllReplies(pageable);
    }


    @PutMapping ("/replies/{id}")
    public String updateReply (@PathVariable Long id, ReplyDTO replyDTO) {
        Long boardId = replyDTO.getBoard_id();
        replyService.updateReply(id,replyDTO);
        return "redirect:/boards/" + boardId;
    }

    @DeleteMapping ("/replies/{id}")
    public String deleteReply (@PathVariable Long id) {
        Long boardId = replyService.findReply(id).getBoard().getId();

        replyService.deleteReply(id);
        return "redirect:/boards/"+boardId;
    }
}
