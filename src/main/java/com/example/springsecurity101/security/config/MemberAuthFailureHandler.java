package com.example.springsecurity101.security.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class MemberAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 세션에 실패 메세지 담기
        session.setAttribute("loginErrorMessage", exception.getMessage());
        // 로그인 실패시 이동할 페이지
        setDefaultFailureUrl("/member/login/login-form?error=true&t=h");
        // 로그인 실패시 이동할 페이지로 이동
        super.onAuthenticationFailure(request, response, exception);
    }
}