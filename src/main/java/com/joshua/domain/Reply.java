package com.joshua.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reply {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String replyTitle;
    private String replyContent;

    @ManyToOne
    @JoinColumn (name = "BOARD_ID")
    private Board board;

    @ManyToOne
    @JoinColumn (name = "MEMBER_ID")
    private Member member;

    @OneToMany (mappedBy = "reply",cascade = CascadeType.ALL)
    private List<Rereply> rereplies;

    @Builder
    public Reply(String replyTitle, String replyContent, Board board, Member member) {
        this.replyTitle = replyTitle;
        this.replyContent = replyContent;
        this.board = board;
        this.member = member;
    }
}















