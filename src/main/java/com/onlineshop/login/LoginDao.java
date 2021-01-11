package com.onlineshop.login;

import java.sql.*;

public class LoginDao {
 
    public Login checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/onlineshop";
        String dbUser = "root";
        String dbPassword = "12345";
 
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM login WHERE username = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
 
        ResultSet result = statement.executeQuery();
 
        Login login = null;
 
        if (result.next()) {
        	login = new Login();
        	login.setPassword(password);
        	login.setUsername(username);
        }
 
        connection.close();
 
        return login;
    }
}