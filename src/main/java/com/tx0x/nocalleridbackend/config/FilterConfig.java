package com.tx0x.nocalleridbackend.config;

import com.tx0x.nocalleridbackend.filter.AuthFilter;
import com.tx0x.nocalleridbackend.jwt.JwtToken;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final JwtToken jwtToken;

    public FilterConfig(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterFilterRegistrationBean() {

        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>(new AuthFilter(jwtToken));
        bean.addUrlPatterns("/api/*");
        bean.setOrder(0);

        return bean;
    }
}
