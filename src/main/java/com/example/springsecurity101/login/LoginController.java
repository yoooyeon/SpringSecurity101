package com.example.springsecurity101.login;

import com.example.springsecurity101.security.auth.MemberPrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/member/main")
    public String main() {
        return "main/main";
    }

    @GetMapping("/member/login/login-form")
    public String login(HttpServletRequest request,
                        @AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails) {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("loginErrorMessage");
        session.setAttribute("loginErrorMessage", msg != null ? msg : "");

        if (isAuthenticated()) {
            if (memberPrincipalDetails == null)
                return "redirect:/logout";
            return "redirect:/member/main";
        }
        return "login/login";
    }


}
