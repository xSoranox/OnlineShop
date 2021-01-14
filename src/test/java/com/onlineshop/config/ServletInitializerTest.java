package com.onlineshop.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServletInitializerTest {

    ServletInitializer servletInitializer = new ServletInitializer();

    @Test
    public void getServletConfigClasses() {
        Class<?>[] result = servletInitializer.getServletConfigClasses();
        assertNotNull(result);
    }

    @Test
    public void getServletMappings() {
        String[] result = servletInitializer.getServletMappings();
        assertNotNull(result);
    }

    @Test
    public void getRootConfigClasses() {
        Class<?>[] result = servletInitializer.getRootConfigClasses();
        assertNull(result);
    }
}