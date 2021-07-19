package com.joshua.service;

import com.joshua.domain.Board;
import com.joshua.domain.Member;
import com.joshua.dto.BoardDTO;
import com.joshua.dto.MemberDTO;
import com.joshua.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long saveMember (MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberAge(memberDTO.getMemberAge());
        return memberRepository.save(member).getId();
    }
}
