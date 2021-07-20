package com.joshua.controller;

import com.joshua.dto.ReReplyDTO;
import com.joshua.service.ReReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReReplyApiController {

    private final ReReplyService reReplyService;

    @PostMapping("/rereplies/reply/{replyId}/member/{memberId}")
    public Long saveReReply (@PathVariable Long replyId, @PathVariable Long memberId, ReReplyDTO reReplyDTO) {
        return reReplyService.saveReReply(replyId, memberId, reReplyDTO);
    }

    @GetMapping ("/rereplies/{id}")
    public ReReplyDTO getReReply (@PathVariable Long id) {
        return reReplyService.getReReply(id);
    }

    @PutMapping ("/rereplies/{id}")
    public Long updateReReply (@PathVariable Long id, ReReplyDTO reReplyDTO) {
        return reReplyService.updateReReply(id, reReplyDTO);
    }

    @DeleteMapping ("/rereplies/{id}")
    public void deleteReReply (@PathVariable Long id) {
        reReplyService.deleteReReply(id);
    }
 }
