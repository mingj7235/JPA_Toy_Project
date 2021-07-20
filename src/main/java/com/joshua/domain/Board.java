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
public class Board {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardTitle;
    private String boardContent;

    @ManyToOne
    @JoinColumn (name = "MEMBER_ID")
    private Member member;

    @Builder
    public Board(String boardTitle, String boardContent, Member member) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.member = member;
    }
}
