package com.onlineshop.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaConfigTest {

    @InjectMocks
    private JpaConfig jpaConfig;
    @Mock
    private Environment environment;
    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void dataSource() {
        when(environment.getRequiredProperty("database.driver"))
                .thenReturn("com.mysql.cj.jdbc.Driver");
        when(environment.getRequiredProperty("database.url"))
                .thenReturn("jdbc:mysql://localhost:3306/onlineshop");
        when(environment.getRequiredProperty("database.user.name"))
                .thenReturn("root");
        when(environment.getRequiredProperty("database.user.password"))
                .thenReturn("12345");

        DataSource result = jpaConfig.dataSource();
        assertNotNull(result);
    }

    @Test
    public void entityManagerFactory() {
        when(environment.getRequiredProperty("database.driver"))
                .thenReturn("com.mysql.cj.jdbc.Driver");
        when(environment.getRequiredProperty("database.url"))
                .thenReturn("jdbc:mysql://localhost:3306/onlineshop");
        when(environment.getRequiredProperty("database.user.name"))
                .thenReturn("root");
        when(environment.getRequiredProperty("database.user.password"))
                .thenReturn("12345");

        EntityManagerFactory result = jpaConfig.entityManagerFactory();
        assertNotNull(result);
    }

    @Test
    public void transactionManager() {
        JpaTransactionManager result = jpaConfig.transactionManager(entityManagerFactory);
        assertNotNull(result);
        assertEquals(result.getEntityManagerFactory(), entityManagerFactory);
    }
}