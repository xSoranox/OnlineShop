package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.Product;
import com.onlineshop.service.ProductService;

@Controller
public class AdminModeController {
	
    @Autowired
    private ProductService productService;
	
    @RequestMapping("/adminmode")
    public ModelAndView failedConnection() {
    	List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
