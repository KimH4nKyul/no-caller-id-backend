package com.tx0x.nocalleridbackend.filter;

import com.tx0x.nocalleridbackend.jwt.JwtToken;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthFilter implements Filter {

    private final JwtToken jwtToken;

    public AuthFilter(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setCharacterEncoding("utf-8");

        String authHeader = req.getHeader("Authorization");
        if(authHeader != null) {
            jwtToken.verificationJwtToken(authHeader);
            filterChain.doFilter(req, res);
        } else {
            res.setStatus(401);
            PrintWriter writer = res.getWriter();
            writer.println("No authorization");
        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
