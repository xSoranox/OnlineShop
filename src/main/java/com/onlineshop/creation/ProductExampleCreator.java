package com.onlineshop.creation;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    public Set<Product> getExampleOfProducts() {
        Set<Product> products = new HashSet<>();

        Product product1 = createProduct("Pepsi", Category.DRINKS.getValue(),
                Drinks.SOFT_DRINKS.getValue(), BigDecimal.valueOf(1.27), BigDecimal.valueOf(22));
        Product product2 = createProduct("Milka", Category.SWEETS_AND_SNACKS.getValue(),
                SweetsAndSnacks.BISCUITS.getValue(), BigDecimal.valueOf(1.30), BigDecimal.ZERO);
        Product product3 = createProduct("Schogetten", Category.SWEETS_AND_SNACKS.getValue(),
                SweetsAndSnacks.CHOCOLATE.getValue(), BigDecimal.valueOf(1.25), BigDecimal.ZERO);
        Product product4 = createProduct("Salmon fillet", Category.MEAT_AND_FISH.getValue(),
                MeatAndFish.FRESH_FISH.getValue(), BigDecimal.valueOf(6.99), BigDecimal.ZERO);
        Product product5 = createProduct("Сheddar cheese", Category.DAIRY_AND_EGGS.getValue(),
                DairyAndEggs.CHEESE.getValue(), BigDecimal.valueOf(7.49), BigDecimal.ZERO);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        return products;

    }

    private Product createProduct(String name, String category, String subcategory, BigDecimal priceBeforeDiscount,
                                  BigDecimal discount) {
        Product product =  new ProductBuilder(name, category, subcategory, priceBeforeDiscount)
                .setDiscount(discount).build();
        product.setEndPrice(endPriceCalculator.calculateEndPrice(product));
        Product savedProduct = repository.save(product);
        return savedProduct;
    }
}
