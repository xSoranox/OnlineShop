package com.onlineshop.login;

import java.io.*;
import java.sql.SQLException;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        LoginDao userDao = new LoginDao();
         
        try {
            Login login = userDao.checkLogin(username, password);
            String destPage = "connectadmin";
             
            if (login != null) {
                HttpSession session = request.getSession();
                session.setAttribute("login", login);
                destPage = "adminmode";
            } else {
            	destPage = "connectadmin/failed";
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
}
