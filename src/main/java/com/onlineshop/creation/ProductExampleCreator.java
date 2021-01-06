package com.onlineshop.creation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlineshop.enumeration.subcategory.DairyAndEggs;
import com.onlineshop.enumeration.subcategory.Drinks;
import com.onlineshop.enumeration.subcategory.MeatAndFish;
import com.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.Product;
import com.onlineshop.enumeration.Category;
import com.onlineshop.enumeration.subcategory.SweetsAndSnacks;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductExampleCreator {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private EndPriceCalculator endPriceCalculator;

    public void createExampleOfProducts() {

        createProduct("Pepsi", Category.DRINKS.getValue(),
                Drinks.SOFT_DRINKS.getValue(), BigDecimal.valueOf(1.27), BigDecimal.valueOf(22));
        createProduct("Milka", Category.SWEETS_AND_SNACKS.getValue(),
                SweetsAndSnacks.BISCUITS.getValue(), BigDecimal.valueOf(1.30), BigDecimal.ZERO);
        createProduct("Schogetten", Category.SWEETS_AND_SNACKS.getValue(),
                SweetsAndSnacks.CHOCOLATE.getValue(), BigDecimal.valueOf(1.25), BigDecimal.ZERO);
        createProduct("Salmon fillet", Category.MEAT_AND_FISH.getValue(),
                MeatAndFish.FRESH_FISH.getValue(), BigDecimal.valueOf(6.99), BigDecimal.ZERO);
        createProduct("Cheddar cheese", Category.DAIRY_AND_EGGS.getValue(),
                DairyAndEggs.CHEESE.getValue(), BigDecimal.valueOf(7.49), BigDecimal.ZERO);
    }

    private void createProduct(String name, String category, String subcategory, BigDecimal priceBeforeDiscount,
                                  BigDecimal discount) {
        Product product =  new ProductBuilder(name, category, subcategory, priceBeforeDiscount)
                .setDiscount(discount).build();
        product.setEndPrice(endPriceCalculator.calculateEndPrice(product));
        repository.save(product);
    }
}
