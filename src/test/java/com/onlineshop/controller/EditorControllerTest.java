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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, ApiConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class EditorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getAllEditorProducts() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/adminmode/editor/")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("list-editor-products", result.getModelAndView().getViewName());
    }

    @Test
    public void addProductToEditor() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/editor/add/{productId}/", 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/adminmode/", result.getModelAndView().getViewName());
    }

    @Test
    public void setDiscountToEditorProducts() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/editor/setDiscount/").param("discount", "50"))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/adminmode/editor/", result.getModelAndView().getViewName());
    }

    @Test
    public void deleteProductsFromEditor() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/adminmode/editor/flush")).andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals( "redirect:/adminmode/editor/", result.getModelAndView().getViewName());
    }

    @Test
    public void deleteSingleProductFromEditor() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/adminmode/editor/delete/{productId}/", 1L))
                .andDo(print())
                .andExpect(status().is(302)).andReturn();

        assertEquals("redirect:/adminmode/editor/", result.getModelAndView().getViewName());
    }
}