package com.joshua.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
//@Entity
public class Rereply {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String reReplyTitle;
    private String reReplyContent;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "REPLY_ID")
    private Reply reply;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "MEMBER_ID")
    private Member member;

    @Builder
    public Rereply(String reReplyTitle, String reReplyContent, Reply reply, Member member) {
        this.reReplyTitle = reReplyTitle;
        this.reReplyContent = reReplyContent;
        this.reply = reply;
        this.member = member;
    }
}
