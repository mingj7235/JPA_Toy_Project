package com.joshua.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rereply {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String reReplyTitle;
    private String reReplyContent;

    @ManyToOne
    @JoinColumn (name = "REPLY_ID")
    private Reply reply;

    @Builder
    public Rereply(String reReplyTitle, String reReplyContent, Reply reply) {
        this.reReplyTitle = reReplyTitle;
        this.reReplyContent = reReplyContent;
        this.reply = reply;
    }
}
