package com.joshua.domain.member.entity;

import com.joshua.global.common.base.BaseTime;
import com.joshua.global.common.enumerate.Gender;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Member extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String name;

    private Long age;

    @Enumerated (value = EnumType.STRING)
    private Gender gender;

}
