package com.joshua.legacy.dto;

import com.joshua.legacy.domain.Gender;
import com.joshua.legacy.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    private String memberName;
    private Integer memberAge;
    private Gender gender;

    public MemberDTO (Member entity) {
        this.memberId = entity.getId();
        this.memberName = entity.getMemberName();
        this.memberAge = entity.getMemberAge();
        this.gender = entity.getGender();
    }

    public Member toEntity () {
        return Member.builder()
                .memberName(memberName)
                .memberAge(memberAge)
                .gender(gender)
                .build();
    }
}
