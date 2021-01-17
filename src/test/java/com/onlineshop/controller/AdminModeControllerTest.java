package com.onlineshop.controller;

import com.onlineshop.config.JpaConfig;
import com.onlineshop.config.WebMvcConfig;
import com.onlineshop.creation.ProductBuilder;
import com.onlineshop.domain.Product;
import com.onlineshop.enumeration.Category;
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

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, ApiConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class AdminModeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getAdminHome() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/adminmode/")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("admin-mode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void findProductsByName() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/searchByName").param("productName", "Milka"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("admin-mode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void findProductsById() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/searchById").param("productId", "1"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("admin-mode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void getProductsByType() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/findbytype/{productType}", "breadAndPastries"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("admin-mode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void deleteProductFromShoppingCart() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/delete/{productId}", 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/adminmode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void editProduct() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/editProduct/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("edit-product", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void saveEditedBook() throws Exception {
        Product product = new ProductBuilder("testProduct", "breadAndPastries",
                BigDecimal.valueOf(60)).setDiscount(BigDecimal.valueOf(50)).build();
        product.setEndPrice(BigDecimal.valueOf(30));
        product.setId(1L);

        MvcResult result = this.mockMvc.perform(post("/adminmode/editProduct/saveEditedProduct/")
                .flashAttr("product", product))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/adminmode", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void createProduct() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/adminmode/createProduct")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("new-product", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }
}