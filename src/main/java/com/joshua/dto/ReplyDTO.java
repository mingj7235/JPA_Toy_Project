package com.joshua.dto;

import com.joshua.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDTO {

    private Long replyId;
    private String replyTitle;
    private String replyContent;
    private Long board_id;
    private Long member_id;
    private List<ReReplyDTO> reReplies;

    public ReplyDTO (Reply entity) {
        this.replyId = entity.getId();
        this.replyTitle = entity.getReplyTitle();
        this.replyContent = entity.getReplyContent();
        this.board_id = entity.getBoard().getId();
        this.member_id = entity.getMember().getId();
        this.reReplies = entity.getRereplies().stream()
                .map(ReReplyDTO::new).collect(Collectors.toList());
    }

    public Reply toEntity () {
        return Reply.builder()
                .replyTitle(replyTitle)
                .replyContent(replyContent)
                .build();
    }


}
