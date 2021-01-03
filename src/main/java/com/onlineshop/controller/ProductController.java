package com.onlineshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.Product;
import com.onlineshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/products")
    public ModelAndView getAllProducts() {
        System.out.println("HAIL HITLER!!!");
        List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("list-products");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("/example")
    public List<Product> getExampleOfProducts() {
        return productService.findAllProducts();
    }

    @RequestMapping("/{productName}")
    public List<Product> findProductsByName(@PathVariable String productName) {
        return productService.findProductsByName(productName);
    }

}
