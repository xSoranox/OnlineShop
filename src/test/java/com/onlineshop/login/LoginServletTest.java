package com.onlineshop.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private LoginServlet loginServlet;

    @Test
    public void testDoPostWhenLoginIsNotNull() throws SQLException, ClassNotFoundException {
        when(request.getParameter("username")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("12345");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getRequestDispatcher("adminmode")).thenReturn(requestDispatcher);


        try {
            loginServlet.doPost(request, response);
            verify(requestDispatcher).forward(request, response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoPostWhenLoginIsNull() throws SQLException, ClassNotFoundException {
        when(request.getParameter("username")).thenReturn("wrongUsername");
        when(request.getParameter("password")).thenReturn("wrongPassword");
        when(request.getRequestDispatcher("connectadmin/failed")).thenReturn(requestDispatcher);

        try {
            loginServlet.doPost(request, response);
            verify(requestDispatcher).forward(request, response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoPostException() throws SQLException, ClassNotFoundException {
        when(request.getParameter("username")).thenReturn("wrongUsername");
        when(request.getParameter("password")).thenReturn("wrongPassword");
        when(request.getRequestDispatcher("connectadmin/failed")).thenReturn(requestDispatcher);

        try {
            loginServlet.doPost(request, response);
            verify(requestDispatcher).forward(request, response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}