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
public class Member {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;
    private Integer memberAge;

//    @OneToMany (mappedBy = "member")
//    private Board board;

    @Builder
    public Member(String memberName, Integer memberAge) {
        this.memberName = memberName;
        this.memberAge = memberAge;
    }
}