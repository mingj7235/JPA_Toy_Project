package com.joshua.dto;

import com.joshua.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDTO {

    private Long replyId;
    private String replyTitle;
    private String replyContent;
    private Long board_id;
    private Long member_id;
    private Long super_reply_id;
    private Integer level;
    private boolean isLive;
//    private List<ReReplyDTO> reReplies;

    public ReplyDTO (Reply entity) {
        this.replyId = entity.getId();
        this.replyTitle = entity.getReplyTitle();
        this.replyContent = entity.getReplyContent();
        this.board_id = entity.getBoard().getId();
        this.member_id = entity.getMember().getId();
        this.level = entity.getLevel();

        if (entity.getSuperReply().getId() == null) {
            this.super_reply_id = 0L;
        } else {
            this.super_reply_id = entity.getSuperReply().getId();
        }

        this.isLive = entity.isLive();
//        this.reReplies = entity.getRereplies().stream()
//                .map(ReReplyDTO::new).collect(Collectors.toList());
    }

    public Reply toEntity () {
        return Reply.builder()
                .replyTitle(replyTitle)
                .replyContent(replyContent)
                .build();
    }


}
