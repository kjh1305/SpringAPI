/*
package com.boot.springapi.security;

import com.boot.springapi.exhandler.ErrorResult;
import com.boot.springapi.exhandler.ExceptionHandlerAdvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //헤더에서 JWT 를 받아옴
        String token = jwtTokenProvider.resolveToken(req);
        log.info("jwt-token-request : {}", token);
        //유효한 토큰인지 확인
        if(token != null & jwtTokenProvider.validateToken(token)){
            log.info("토큰 유효확인");
            //토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            log.info("토큰 -> 유저 정보 : {}", authentication);
            //SecurityContext 에 Authentication 객체를 저장, 현재 로그인한 정보
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }
}
*/
