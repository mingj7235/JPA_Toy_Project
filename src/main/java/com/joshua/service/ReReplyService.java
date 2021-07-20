package com.joshua.service;

import com.joshua.domain.Reply;
import com.joshua.domain.Rereply;
import com.joshua.dto.ReReplyDTO;
import com.joshua.repository.ReReplyRepository;
import com.joshua.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReReplyService {
    private final ReReplyRepository reReplyRepository;
    private final ReplyRepository replyRepository;

    public Long saveReReply (Long replyId, ReReplyDTO reReplyDTO) {
        Rereply rereply = reReplyDTO.toEntity();
        rereply.setReply(findReply(replyId));

        return reReplyRepository.save(rereply).getId();
    }

    public Reply findReply (Long id) {
        return replyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("부모댓글이 없어요"));
    }

    public Rereply findReReply (Long id) {
        return reReplyRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("찾는 대댓글이 없어요"));
    }

}
