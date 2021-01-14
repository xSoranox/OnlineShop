package com.onlineshop.calculations;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EndPriceCalculatorTest {

    EndPriceCalculator endPriceCalculator = new EndPriceCalculator();

    @Test
    public void testCalculateOriginalProductEndPriceNonNullFlow() {
        Product product = new Product();
        product.setDiscount(BigDecimal.valueOf(50));
        product.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        BigDecimal result = endPriceCalculator.calculateOriginalProductEndPrice(product);
        assertEquals(result.intValue(), 30);
    }

    @Test
    public void testCalculateOriginalProductEndPriceNullFlow() {
        Product product = new Product();
        product.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        BigDecimal result = endPriceCalculator.calculateOriginalProductEndPrice(product);
        assertEquals(result, BigDecimal.valueOf(60));
    }

    @Test
    public void testCalculateCartProductEndPriceNonNullFlow() {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(2);
        cartProduct.setDiscount(BigDecimal.valueOf(50));
        cartProduct.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        BigDecimal result = endPriceCalculator.calculateCartProductEndPrice(cartProduct);
        assertEquals(result.intValue(), 60);
    }

    @Test
    public void testCalculateCartProductEndPriceNullFlow() {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(2);
        cartProduct.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        BigDecimal result = endPriceCalculator.calculateCartProductEndPrice(cartProduct);
        assertEquals(result, BigDecimal.valueOf(120));
    }

    @Test
    public void testCalculateTotalSum() {
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct1 = new CartProduct();
        cartProduct1.setEndPrice(BigDecimal.valueOf(50));
        CartProduct cartProduct2 = new CartProduct();
        cartProduct2.setEndPrice(BigDecimal.valueOf(40));
        cartProductList.add(cartProduct1);
        cartProductList.add(cartProduct2);
        BigDecimal result = endPriceCalculator.calculateTotalSum(cartProductList);
        assertEquals(result, BigDecimal.valueOf(90));
    }
}