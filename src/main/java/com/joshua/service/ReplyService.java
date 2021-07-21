package com.joshua.service;

import com.joshua.domain.Reply;
import com.joshua.dto.ReplyDTO;
import com.joshua.repository.BoardRepository;
import com.joshua.repository.MemberRepository;
import com.joshua.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        //supReply create
        if (replyDTO.getSuper_reply_id() == null || replyDTO.getSuper_reply_id() == 0L) {
            reply.setLevel(1);
        }

        //subReply create
        else {
            Long supReplyId = replyDTO.getSuper_reply_id();
            Reply supReply = replyRepository.findById(supReplyId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글 찾기 오류"));

            //supReply dead
            if (!supReply.isLive()) {
                throw new RuntimeException("부모 댓글이 이미 삭제되었습니다. ");
            }
            //supReply live
            reply.setLevel(supReply.getLevel()+1);
            reply.setSuperReply(supReply);
            supReply.getSubReply().add(reply);

        }
//        reply.setReplyTitle(replyDTO.getReplyTitle());
//        reply.setReplyContent(replyDTO.getReplyContent());
        reply.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다")));
        reply.setBoard(boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판이 아닙니다.")));
        reply.setLive(true);
        return replyRepository.save(reply).getId();
    }

    public ReplyDTO getReply (Long id) {
        Reply reply = findReply(id);

        return new ReplyDTO(reply);
    }

    public Page<ReplyDTO> getAllReplies (Pageable pageable) {
        PageRequest id = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        Page<ReplyDTO> replyList = replyRepository.findAll(id).map(ReplyDTO::new);
        return replyList;
    }

    public Long updateReply (Long id, ReplyDTO replyDTO) {
        Reply reply = findReply(id);

        reply.setReplyTitle(replyDTO.getReplyTitle());
        reply.setReplyContent(replyDTO.getReplyContent());
        return reply.getId();
    }


    public void deleteReply (Long id) {
        Reply reply = findReply(id);

        if(reply.getSubReply().size() == 0) { //subReply 없을 경우

            while (reply != null) {
                Reply superReply = reply.getSuperReply();

                if(superReply == null) { //sub도 없고 supReply 없을 경우 즉, 자식 댓글이 없는 supReply일경우는 그냥 삭제
                    replyRepository.deleteById(reply.getId());
                    break;
                }
                //supReply 있을 경우 supReply에서 찾아서 제거
                superReply.getSubReply().remove(reply); //이작업 안하니까 오류남
                replyRepository.deleteById(reply.getId());

                //while문 빠져나가기 위해 reply를 superReply에 치환
                if(superReply.getSubReply().size() == 0 && !superReply.isLive()) {
                    reply = superReply;
                }
                else {break;}
            }
        }
        else if (reply != null) {
            reply.setReplyContent("삭제된 댓글임");
            reply.setLive(false);
        }
    }


    //반복 로직 메소드화
    public Reply findReply (Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 없습니다. "));
    }

}
