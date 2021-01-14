package com.onlineshop.service.validations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminAuthenticationFilterTest {

    @Mock
    private ServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession httpSession;
    @InjectMocks
    private AdminAuthenticationFilter adminAuthenticationFilter;

    @Test
    public void testDoFilterFirstIfFlow() {
        when(request.getContextPath()).thenReturn("localhost:8081/onlineshop");
        when(request.getRequestURI()).thenReturn("localhost:8081/onlineshop/connectadmin");
        when(request.getRequestDispatcher("/adminmode/")).thenReturn(requestDispatcher);
        when(request.getSession(false)).thenReturn(httpSession);
        when(httpSession.getAttribute("login")).thenReturn(new Object());

        try {
            adminAuthenticationFilter.doFilter(request, response, chain);
            verify(requestDispatcher).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoFilterSecondIfFlow() {
        when(request.getContextPath()).thenReturn("localhost:8081/onlineshop/products");
        when(request.getRequestURI()).thenReturn("localhost:8081/onlineshop/connectadmin");
        when(request.getSession(false)).thenReturn(httpSession);
        when(httpSession.getAttribute("login")).thenReturn(new Object());

        try {
            adminAuthenticationFilter.doFilter(request, response, chain);
            verify(chain).doFilter(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoFilterElseFlow() {
        when(request.getContextPath()).thenReturn("localhost:8081/onlineshop/products");
        when(request.getRequestURI()).thenReturn("localhost:8081/onlineshop/adminmode/edit");
        when(request.getRequestDispatcher("/connectadmin")).thenReturn(requestDispatcher);

        try {
            adminAuthenticationFilter.doFilter(request, response, chain);
            verify(requestDispatcher).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void destroy() {
        adminAuthenticationFilter.destroy();
    }

    @Test
    public void init() {
        try {
            adminAuthenticationFilter.init(filterConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}