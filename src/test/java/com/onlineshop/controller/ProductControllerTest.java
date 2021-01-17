package com.onlineshop.controller;

import com.onlineshop.config.JpaConfig;
import com.onlineshop.config.WebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, ApiConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/products/")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("list-products", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void testGetExampleOfProducts() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/products/example/")).andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/products", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void testFindProductsByName() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/products/search").param("productName", "Milka"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("list-products", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void testGetProductsByType() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/products/findbytype/{productType}/", "breadAndPastries"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("list-products", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void testFlush() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/products/flush/")).andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }
}