package com.joshua.controller;

import com.joshua.dto.ReReplyDTO;
import com.joshua.service.ReReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReReplyApiController {

    private final ReReplyService reReplyService;

    @PostMapping("/rereplies/{replyId}")
    public Long saveReReply (@PathVariable Long replyId, ReReplyDTO reReplyDTO) {
        return reReplyService.saveReReply(replyId, reReplyDTO);
    }
}
