package com.example.springsecurity101.member.service;

import com.example.springsecurity101.member.entity.Member;
import com.example.springsecurity101.member.repository.MemberRepository;
import com.example.springsecurity101.member.request.dto.MemberRequestDto;
import com.example.springsecurity101.member.response.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto join(MemberRequestDto dto) {
        String bcryptPassword = BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt());
        dto.setPassword(bcryptPassword);
        Member member = memberRepository.save(dto.toEntity());
        return MemberResponseDto.fromEntity(member);
    }
}
