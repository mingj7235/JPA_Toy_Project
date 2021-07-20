package com.joshua.controller;

import com.joshua.dto.ReReplyDTO;
import com.joshua.service.ReReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReReplyApiController {

    private final ReReplyService reReplyService;

    @PostMapping("/rereplies/{replyId}")
    public Long saveReReply (@PathVariable Long replyId, ReReplyDTO reReplyDTO) {
        return reReplyService.saveReReply(replyId, reReplyDTO);
    }

    @GetMapping ("/rereplies/{id}")
    public ReReplyDTO getReReply (Long id) {
        return reReplyService.getReReply(id);
    }

    @PutMapping ("/rereplies/{id}")
    public Long updateReReply (Long id, ReReplyDTO reReplyDTO) {
        return reReplyService.updateReReply(id, reReplyDTO);
    }

    @DeleteMapping ("/rereplies/{id}")
    public void deleteReReply (Long id) {
        reReplyService.deleteReReply(id);
    }
 }
