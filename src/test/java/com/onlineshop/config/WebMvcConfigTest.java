package com.onlineshop.config;

import org.junit.Test;
import org.springframework.web.servlet.ViewResolver;

import static org.junit.Assert.*;

public class WebMvcConfigTest {

    @Test
    public void viewResolver() {
        WebMvcConfig webMvcConfig = new WebMvcConfig();
        ViewResolver result = webMvcConfig.viewResolver();
        assertNotNull(result);
    }
}