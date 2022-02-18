package com.joshua.legacy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@Entity
public class Reply {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String replyTitle;

    @Column (nullable = false)
    private String replyContent;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "BOARD_ID")
    private Board board;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "MEMBER_ID")
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "super_reply_id")
    private Reply superReply;

    @OneToMany (mappedBy = "superReply", cascade = CascadeType.ALL)
    private List<Reply> subReply = new ArrayList<>();

    private Integer level;

    private boolean live;

//    @OneToMany (mappedBy = "reply",cascade = CascadeType.ALL)
//    private List<Rereply> rereplies;

    @Builder
    public Reply(String replyTitle, String replyContent, Board board, Member member, Reply superReply) {
        this.replyTitle = replyTitle;
        this.replyContent = replyContent;
        this.board = board;
        this.member = member;
        this.superReply = superReply;
    }
}















