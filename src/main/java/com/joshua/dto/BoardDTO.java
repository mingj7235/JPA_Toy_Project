package com.joshua.dto;

import com.joshua.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private String boardTitle;
    private String boardContent;
    private Long member_id;
    private List <ReplyDTO> replies;

    public BoardDTO (Board entity) {
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.member_id = entity.getMember().getId();
        this.replies = entity.getReplies().stream()
                .map(ReplyDTO::new).collect(Collectors.toList());
    }

    public Board toEntity () {
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .build();
    }
}
