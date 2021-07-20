package com.joshua.controller;

import com.joshua.dto.MemberDTO;
import com.joshua.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping ("/members")
    public void saveMember (MemberDTO memberDTO) {
        memberService.saveMember(memberDTO);
    }

}
