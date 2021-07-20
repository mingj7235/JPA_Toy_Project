package com.joshua.dto;

import com.joshua.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private String memberName;
    private Integer memberAge;

    public MemberDTO (Member entity) {
        this.memberName = entity.getMemberName();
        this.memberAge = entity.getMemberAge();
    }

    public Member toEntity () {
        return Member.builder()
                .memberName(memberName)
                .memberAge(memberAge)
                .build();
    }
}
