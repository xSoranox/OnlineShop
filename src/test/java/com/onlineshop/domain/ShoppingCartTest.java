package com.onlineshop.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ShoppingCartTest {

    @Test
    public void getTotalCost() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("name");
        shoppingCart.setId(1L);
        shoppingCart.setProducts(getCartProductSet());
        Long expectedLong = 1L;
        BigDecimal result = shoppingCart.getTotalCost();
        assertEquals(result, BigDecimal.valueOf(15));
        assertEquals(shoppingCart.getName(), "name");
        assertEquals(shoppingCart.getId(), expectedLong);
    }

    private Set<CartProduct> getCartProductSet() {
        Set<CartProduct> cartProductSet = new HashSet<>();
        CartProduct cartProduct1 = new CartProduct();
        cartProduct1.setId(111L);
        cartProduct1.setEndPrice(BigDecimal.valueOf(10));
        CartProduct cartProduct2 = new CartProduct();
        cartProduct2.setId(222L);
        cartProduct2.setEndPrice(BigDecimal.valueOf(5));
        cartProductSet.add(cartProduct1);
        cartProductSet.add(cartProduct2);

        Long expectedLongId1 = 111L;
        Long expectedLongId2 = 222L;
        assertEquals(cartProduct1.getId(), expectedLongId1);
        assertEquals(cartProduct2.getId(), expectedLongId2);
        return cartProductSet;
    }

    @Test
    public void testIsInEditor() {
        Product product = new Product();
        product.setInEditor(true);
        assertTrue(product.isInEditor());
    }
}