package com.onlineshop.login;

import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class LoginDaoTest {

    LoginDao loginDao = new LoginDao();

    @Test
    public void checkLogin() throws SQLException {
        try {
            Login result = loginDao.checkLogin("root", "12345");

            assertEquals(result.getUsername(), "root");
            assertEquals(result.getPassword(), "12345");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}