package com.onlineshop.controller;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.service.CartProductService;
import com.onlineshop.service.ProductService;
import com.onlineshop.service.ProductTypeService;
import com.onlineshop.service.ShoppingCartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ApiConfig {

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartService();
    }

    @Bean
    public ProductExampleCreator productExampleCreator() {
        return new ProductExampleCreator();
    }

    @Bean
    public EndPriceCalculator endPriceCalculator() {
        return new EndPriceCalculator();
    }

    @Bean
    public ProductTypeService productTypeService() {
        return new ProductTypeService();
    }
    
    @Bean
    public CartProductService cartProductService() {
        return new CartProductService();
    }
}
