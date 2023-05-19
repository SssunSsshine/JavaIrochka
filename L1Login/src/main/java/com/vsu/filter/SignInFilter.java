package com.vsu.filter;

import com.vsu.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class SignInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String contextPath = httpServletRequest.getContextPath();
        String loginURI = contextPath + "/login";
        Set<String> uris = new HashSet<>(Arrays.asList(loginURI, contextPath + "/new", contextPath + "/user/signup", contextPath + "/sign-in"));
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);

        User user = (User) session.getAttribute("user");
        if (loginRequest && user != null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/page");
            return;
        } else if (!uris.contains(httpServletRequest.getRequestURI()) && user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }
}
