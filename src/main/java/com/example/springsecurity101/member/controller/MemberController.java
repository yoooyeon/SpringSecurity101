package com.example.springsecurity101.member.controller;

import com.example.springsecurity101.member.entity.Member;
import com.example.springsecurity101.member.request.dto.MemberRequestDto;
import com.example.springsecurity101.member.response.dto.MemberResponseDto;
import com.example.springsecurity101.member.service.MemberService;
import com.example.springsecurity101.security.auth.MemberPrincipalDetails;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public String join(Model model, @RequestBody MemberRequestDto dto) {
        MemberResponseDto member = memberService.join(dto);
        model.addAttribute("member", member);
        return "redirect:/member/main";
    }

    @GetMapping("/join")
    public String join() {
        return "login/join";
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails
            , Model model) {
        Member member = memberPrincipalDetails.member();
        model.addAttribute("member", member);
        return "info/info";
    }
}
