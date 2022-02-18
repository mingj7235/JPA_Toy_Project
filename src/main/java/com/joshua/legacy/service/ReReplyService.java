package com.joshua.legacy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReReplyService {
//    private final ReReplyRepository reReplyRepository;
//    private final ReplyRepository replyRepository;
//    private final MemberRepository memberRepository;
//
//    public Long saveReReply (Long replyId, Long memberId, ReReplyDTO reReplyDTO) {
//        Rereply rereply = reReplyDTO.toEntity();
//        rereply.setReply(findReply(replyId));
//        rereply.setMember(memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다.")));
//
//        return reReplyRepository.save(rereply).getId();
//    }
//
//    public ReReplyDTO getReReply (Long id) {
//        Rereply rereply = findReReply(id);
//        return new ReReplyDTO(rereply);
//    }
//
//    public Long updateReReply (Long id, ReReplyDTO reReplyDTO) {
//        Rereply rereply = findReReply(id);
//        rereply.setReReplyTitle(reReplyDTO.getReReplyTitle());
//        rereply.setReReplyContent(reReplyDTO.getReReplyContent());
//        return rereply.getId();
//    }
//
//    public void deleteReReply (Long id) {
//        Rereply rereply = findReReply(id);
//        reReplyRepository.delete(rereply);
//    }
//
//    public Reply findReply (Long id) {
//        return replyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("부모댓글이 없어요"));
//    }
//
//    public Rereply findReReply (Long id) {
//        return reReplyRepository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("찾는 대댓글이 없어요"));
//    }

}
