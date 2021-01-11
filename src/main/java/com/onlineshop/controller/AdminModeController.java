package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping("/adminmode/searchByName")
    public ModelAndView findProductsByName(@RequestParam String productName) {
        List<Product> products = productService.findProductsByName(productName);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/searchById")
    public ModelAndView findProductsById(@RequestParam String productId) {
        List<Product> products = productService.findProductsById(productId);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/findbytype/{productType}")
    public ModelAndView getProductsByType(@PathVariable("productType") String productType) {
        List<Product> products = productService.findProductsByType(productType);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
