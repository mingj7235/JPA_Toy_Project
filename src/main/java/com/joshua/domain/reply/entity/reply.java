package com.joshua.domain.reply.entity;

import com.joshua.domain.board.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class reply {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String subject;

    @Column (nullable = false)
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    private Board board;
}
