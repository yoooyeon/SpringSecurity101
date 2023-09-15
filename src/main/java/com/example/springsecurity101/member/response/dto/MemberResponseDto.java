package com.example.springsecurity101.member.response.dto;

import com.example.springsecurity101.member.entity.Member;
import com.example.springsecurity101.member.entity.MemberType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String name;
    @Email
    private String email;
    private String loginId;
    private MemberType role;

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .name(member.getName())
                .loginId(member.getLoginId())
                .build();
    }


}
