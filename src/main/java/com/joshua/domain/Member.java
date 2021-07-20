package com.joshua.domain;

import com.sun.tools.javac.jvm.Gen;
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

    @Enumerated (value = EnumType.STRING)
    private Gender gender;

//    @OneToMany (mappedBy = "member")
//    private Board board;

    @Builder
    public Member(String memberName, Integer memberAge, Gender gender) {
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.gender = gender;
    }
}
