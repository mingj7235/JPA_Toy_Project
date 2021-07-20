package com.joshua.dto;

import com.joshua.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {

    private String boardTitle;
    private String boardContent;
    private Long member_id;

    public BoardDTO (Board entity) {
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.member_id = entity.getMember().getId();
    }

    public Board toEntity () {
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .build();
    }
}
