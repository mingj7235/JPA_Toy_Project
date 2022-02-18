package com.joshua.domain.board.entity;

import com.joshua.domain.member.entity.Member;
import com.joshua.global.common.base.BaseTime;
import com.joshua.legacy.domain.Reply;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Board extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String subject;

    @Column (nullable = false)
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member owner;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "board")
    private List<Reply> replies;

}
