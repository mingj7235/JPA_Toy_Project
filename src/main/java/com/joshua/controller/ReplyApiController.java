package com.joshua.controller;

import com.joshua.dto.ReplyDTO;
import com.joshua.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping ("/replies/boards/{boardId}/members/{memberId}")
    public Long saveReply (@PathVariable Long boardId, @PathVariable Long memberId, ReplyDTO replyDTO) {
        return replyService.saveReply(boardId, memberId, replyDTO);
    }

    @GetMapping ("/replies/{id}")
    public ReplyDTO getReply (@PathVariable Long id) {
        return replyService.getReply(id);
    }

    @GetMapping ("/replies")
    public Page<ReplyDTO> getAllReplies (Pageable pageable) {
        return replyService.getAllReplies(pageable);
    }


    @PutMapping ("/replies/{id}")
    public Long updateReply (@PathVariable Long id, ReplyDTO replyDTO) {
        return replyService.updateReply(id,replyDTO);
    }

    @DeleteMapping ("/replies/{id}")
    public void deleteReply (@PathVariable Long id) {
        replyService.deleteReply(id);
    }


}
