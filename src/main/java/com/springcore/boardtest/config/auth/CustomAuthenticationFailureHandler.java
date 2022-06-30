package com.springcore.boardtest.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";

        log.info("exception: " + exception);

        if(exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호를 확인해주세요.";
        } else if(exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
        } else if(exception instanceof UsernameNotFoundException) {
            errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
        } else {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");    // 한글 인코딩
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
