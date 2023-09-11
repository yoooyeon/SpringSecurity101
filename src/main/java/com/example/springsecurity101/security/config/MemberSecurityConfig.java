package com.example.springsecurity101.security.config;

import com.example.springsecurity101.security.auth.MemberPrincipalDetailService;
import com.example.springsecurity101.security.provider.MemberAuthenticatorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class MemberSecurityConfig {
    private final MemberAuthenticatorProvider memberAuthenticatorProvider;
    private final MemberPrincipalDetailService memberPrincipalDetailService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(memberAuthenticatorProvider);
    }

    @Bean
    public SecurityFilterChain memberSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable(); // jwt 토큰 사용

        http.authorizeHttpRequests(authorize -> {
            try {
                authorize
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/member/login/**")
                        .permitAll() // 위 경로는 인증 없이 접근 가능
                        .requestMatchers("/member/**")
                        .hasRole("MEMBER") // 위 경로 접근 시 Role에 MEMBER가 포함되어야 함

                        .and()// 로그인 페이지 설정
                        .formLogin()
                        .loginPage("/member/login/login-form") // 로그인 페이지 설정
                        .loginProcessingUrl("/member/login/login") // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/member/main") // 로그인 성공 후 이동할 페이지
                        .successHandler(new MemberAuthSuccessHandler()) // 로그인 성공 후 처리할 핸들러
                        .failureHandler(new MemberAuthFailureHandler()) // 로그인 실패 후 처리할 핸들러
                        .permitAll()

                        .and()// 로그아웃 설정
                        .logout()
                        .logoutUrl("/logout") // 로그아웃 처리 URL 설정
                        .logoutSuccessUrl("/member/login/login-form?logout=1") // 로그아웃 성공 후 이동할 페이지
                        .deleteCookies("JSESSIONID"); // 로그아웃 후 쿠키 삭제

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        http.rememberMe()
                .key("yooyeon") // 인증 토큰 생성 시 사용할 키
                .tokenValiditySeconds(60 * 60 * 24 * 7) // 인증 토큰 유효 시간 - 1주일
                .userDetailsService(memberPrincipalDetailService) // 인증 토큰 생성시 사용할 UserDetailsService
                .rememberMeParameter("remember-me"); // 로그인 페이지에서 사용할 파라미터 이름 (자동로그인)

        return http.build();
    }
}