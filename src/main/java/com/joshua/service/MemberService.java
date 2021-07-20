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
        Member member = memberDTO.toEntity();
        return memberRepository.save(member).getId();
    }

    public MemberDTO getMember (Long id) {
        return new MemberDTO(findMember(id));
    }

    public Long updateMember (Long id, MemberDTO memberDTO) {
        Member member = findMember(id);
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberAge(memberDTO.getMemberAge());
        return member.getId();
    }

    public void deleteMember (Long id) {
        memberRepository.delete(findMember(id));
    }

    public Member findMember (Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 멤버 없습니다."));
    }
}
