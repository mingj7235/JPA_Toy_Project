package com.joshua.controller;

import com.joshua.dto.MemberDTO;
import com.joshua.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping ("/members")
    public Long saveMember (MemberDTO memberDTO) {
        return memberService.saveMember(memberDTO);
    }

    @GetMapping ("/members/{id}")
    public MemberDTO getMember (@PathVariable Long id) {
        MemberDTO memberDTO = memberService.getMember(id);
        return memberDTO;
    }

    @PatchMapping ("/members/{id}")
    public Long updateMember (@PathVariable Long id, MemberDTO memberDTO) {
        return memberService.updateMember(id, memberDTO);
    }

    @DeleteMapping ("/member/{id}")
    public void deleteMember (@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
