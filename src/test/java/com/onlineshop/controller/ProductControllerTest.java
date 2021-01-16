package com.onlineshop.controller;

import com.onlineshop.config.JpaConfig;
import com.onlineshop.config.WebMvcConfig;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.service.ProductService;
import com.onlineshop.service.ShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, ProductControllerConfig.class, 
		WebMvcConfig.class, ProductRepository.class})
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/products/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAllProducts() {
    }

    @Test
    public void getExampleOfProducts() {
    }

    @Test
    public void findProductsByName() {
    }

    @Test
    public void getProductsByType() {
    }

    @Test
    public void flush() {
    }
}