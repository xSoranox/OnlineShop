package com.onlineshop.controller;

import com.onlineshop.config.JpaConfig;
import com.onlineshop.config.WebMvcConfig;
import junit.framework.TestCase;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, ApiConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class ShoppingCartControllerTest extends TestCase {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getAllProducts() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/{cartId}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("list-cart-products", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void addProductToCart() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/addProduct/{cartId}/{productId}", 1L, 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/", Objects.requireNonNull(result.getModelAndView()).getViewName());
    }

    @Test
    public void deleteAllProductsFromShoppingCart() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/flush/{cartId}", 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/shoppingCart/1", Objects.requireNonNull(
                result.getModelAndView()).getViewName());
    }

    @Test
    public void deleteProductFromShoppingCart() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/delete/{cartId}/{productId}", 1L, 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/shoppingCart/1", Objects.requireNonNull(
                result.getModelAndView()).getViewName());
    }

    @Test
    public void reduceCartProductQuantity() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/reduceProduct/{cartId}/{productId}", 1L, 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/shoppingCart/1", Objects.requireNonNull(
                result.getModelAndView()).getViewName());
    }

    @Test
    public void increaseCartProductQuantity() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/shoppingCart/increaseProduct/{cartId}/{productId}", 1L, 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/shoppingCart/1", Objects.requireNonNull(
                result.getModelAndView()).getViewName());
    }
}