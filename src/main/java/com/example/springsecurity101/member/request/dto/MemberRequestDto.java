package com.example.springsecurity101.member.request.dto;

import com.example.springsecurity101.member.entity.Member;
import com.example.springsecurity101.member.entity.MemberType;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Data
public class MemberRequestDto {
    private String name;
    private String loginId;
    private String password;
    private String phone;
    private MemberType role;

    public Member toEntity() {
        return Member.builder()
                .password(this.getPassword())
                .loginId(this.getLoginId())
                .name(this.getName())
                .role(this.getRole())
                .phone(this.getPhone())
                .build();
    }
}
