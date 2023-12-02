package com.eorhn.bookstore.utilities.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.LogRecord;

@Component
@Order(1)
public class LogFilter implements Filter {


    /**
     * Simple filter for logging requested api information to terminal.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println("Logging request : " + req.getMethod() + req.getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Logging response : " + req.getMethod() + resp.getContentType());
    }
}
