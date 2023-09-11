package com.example.springsecurity101.security.auth;

import com.example.springsecurity101.member.entity.Member;
import com.example.springsecurity101.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberPrincipalDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(username); // username은 id이다.
        log.info("username:" + username);
        log.info("member:" + member);
        if (member == null)
            throw new UsernameNotFoundException(username + "을 찾을 수 없습니다.");
        return new MemberPrincipalDetails(member);
    }
}