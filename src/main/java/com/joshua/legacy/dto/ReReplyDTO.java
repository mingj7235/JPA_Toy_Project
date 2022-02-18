package com.joshua.legacy.dto;

import com.joshua.legacy.domain.Rereply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReReplyDTO {

    private Long reReplyId;
    private String reReplyTitle;
    private String reReplyContent;
    private Long reply_id;
    private Long member_id;

    public ReReplyDTO (Rereply entity) {
        this.reReplyId = entity.getId();
        this.reReplyTitle = entity.getReReplyTitle();
        this.reReplyContent = entity.getReReplyContent();
        this.reply_id = entity.getReply().getId();
        this.member_id = entity.getMember().getId();
    }

    public Rereply toEntity () {
        return Rereply.builder()
                .reReplyTitle(reReplyTitle)
                .reReplyContent(reReplyContent)
                .build();
    }
}
