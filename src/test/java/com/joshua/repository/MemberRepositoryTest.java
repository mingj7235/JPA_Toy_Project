package com.joshua.repository;

import com.joshua.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class MemberRepositoryTest {

//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 회원가입_테스트 () {
//        //given
//        Member member = new Member();
//        member.setId(1L);
//        member.setMemberName("테스트");
//        member.setMemberAge(12);
//
//        //when
//        memberRepository.save(member);
//
//        //then
//        Assertions.assertThat(memberRepository.findById(1L).get().getMemberAge()).isEqualTo(12);
//    }
}