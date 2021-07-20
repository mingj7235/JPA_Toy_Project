package com.joshua.dto;

import com.joshua.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDTO {

    private String replyTitle;
    private String replyContent;
    private Long board_id;
    private Long member_id;

    public ReplyDTO (Reply entity) {
        this.replyTitle = entity.getReplyTitle();
        this.replyContent = entity.getReplyContent();
        this.board_id = entity.getBoard().getId();
        this.member_id = entity.getMember().getId();
    }

    public Reply toEntity () {
        return Reply.builder()
                .replyTitle(replyTitle)
                .replyContent(replyContent)
                .build();
    }


}