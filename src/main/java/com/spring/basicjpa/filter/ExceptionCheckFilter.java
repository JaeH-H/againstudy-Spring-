package com.spring.basicjpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ExceptionCheckFilter implements Filter {

    // WAS -> Filter -> Servlet
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        log.info("::: 필터 호출 URL: {}", requestURI);
        chain.doFilter(request, response);
    }
}
