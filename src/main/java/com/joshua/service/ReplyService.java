package com.joshua.service;

import com.joshua.domain.Reply;
import com.joshua.dto.ReplyDTO;
import com.joshua.repository.BoardRepository;
import com.joshua.repository.MemberRepository;
import com.joshua.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Long saveReply (Long boardId, Long memberId, ReplyDTO replyDTO) {
        Reply reply = replyDTO.toEntity();
        reply.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다")));
        reply.setBoard(boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판이 아닙니다.")));

        return replyRepository.save(reply).getId();
    }

    public ReplyDTO getReply (Long id) {
        Reply reply = findReply(id);

        return new ReplyDTO(reply);
    }

    public Long updateReply (Long id, ReplyDTO replyDTO) {
        Reply reply = findReply(id);

        reply.setReplyTitle(replyDTO.getReplyTitle());
        reply.setReplyContent(replyDTO.getReplyContent());
        return reply.getId();
    }

    public void deleteReply (Long id) {
        replyRepository.delete(findReply(id));
    }


    //반복 로직 메소드화
    public Reply findReply (Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 없습니다. "));
    }

}
