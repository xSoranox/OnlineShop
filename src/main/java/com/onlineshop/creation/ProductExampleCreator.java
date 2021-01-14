package com.onlineshop.creation;

import java.math.BigDecimal;

import com.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.Product;
import com.onlineshop.enumeration.Category;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductExampleCreator {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private EndPriceCalculator endPriceCalculator;

    public void createExampleOfProducts() {

        createProduct("Pepsi", Category.DRINKS.getValue(),BigDecimal.valueOf(1.27), BigDecimal.valueOf(22));
        createProduct("Milka", Category.SWEETS_AND_SNACKS.getValue(), BigDecimal.valueOf(1.30), BigDecimal.ZERO);
        createProduct("Schogetten", Category.SWEETS_AND_SNACKS.getValue(), BigDecimal.valueOf(1.25), BigDecimal.ZERO);
        createProduct("Salmon fillet", Category.MEAT_AND_FISH.getValue(), BigDecimal.valueOf(6.99), BigDecimal.ZERO);
        createProduct("Cheddar cheese", Category.DAIRY_AND_EGGS.getValue(), BigDecimal.valueOf(7.49), BigDecimal.ZERO);
    }

    private void createProduct(String name, String category, BigDecimal priceBeforeDiscount, BigDecimal discount) {
        Product product = new ProductBuilder(name, category, priceBeforeDiscount)
                .setDiscount(discount).build();
        product.setEndPrice(endPriceCalculator.calculateOriginalProductEndPrice(product));
        repository.save(product);
    }
}
