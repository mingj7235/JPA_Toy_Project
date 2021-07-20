package com.joshua.dto;

import com.joshua.domain.Board;
import com.joshua.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardDTO {

    private Long boardId;
    private String boardTitle;
    private String boardContent;
//    private Long member_id;
//    private MemberDTO member;
    //MemberDTO가 아닌, Member로 조회는 여기서는 안전하다. 왜?
    //Member객체는 순환참조할 필드를 가지고 있지 않기 때문이다.
    private Member member;
    private List <ReplyDTO> replies;

    public BoardDTO (Board entity) {
        this.boardId = entity.getId();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
//        this.member_id = entity.getMember().getId();
        this.member = entity.getMember();
        this.replies = entity.getReplies().stream()
                .map(ReplyDTO::new).collect(Collectors.toList());
                //.map(reply -> new ReplyDTO(reply)).collect(Collectors.toList());
    }

    public Board toEntity () {
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .build();
    }
}
