package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ShoppingCart;
import com.onlineshop.enumeration.Category;
import com.onlineshop.repository.CartProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartProductServiceTest {

    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private EndPriceCalculator endPriceCalculator;
    @InjectMocks
    private CartProductService cartProductService;

    @Before
    public void setUp() {
        when(endPriceCalculator.calculateCartProductEndPrice(any())).thenReturn(BigDecimal.valueOf(10));
    }

    @Test
    public void testCreateCartProduct() {
        CartProduct expectedCartProduct = getCartProduct();
        when(cartProductRepository.save(any())).thenReturn(expectedCartProduct);
        CartProduct result = cartProductService.createCartProduct(getProduct(), getShoppingCart());
        assertNotNull(result);
        assertThat(result).isEqualToIgnoringGivenFields(expectedCartProduct, "id");
        verify(cartProductRepository).save(any());
        verifyNoMoreInteractions(cartProductRepository, endPriceCalculator, cartProductRepository);
    }

    @Test
    public void testUpdateQuantityWhenAddingExistingCartProduct() {
        CartProduct cartProduct = getCartProduct();
        cartProductService.updateQuantityWhenAddingExistingCartProduct(cartProduct);
        assertEquals(4, cartProduct.getQuantity());
        assertEquals(BigDecimal.valueOf(10), cartProduct.getEndPrice());
        verify(cartProductRepository).save(cartProduct);
        verify(endPriceCalculator).calculateCartProductEndPrice(cartProduct);
        verifyNoMoreInteractions(cartProductRepository, endPriceCalculator, cartProductRepository);
    }

    @Test
    public void testUpdateQuantityWhenDeleteCartProduct() {
        CartProduct cartProduct = getCartProduct();
        cartProductService.updateQuantityWhenDeleteCartProduct(cartProduct, 5);
        assertEquals(4, cartProduct.getQuantity());
        assertEquals(BigDecimal.valueOf(10), cartProduct.getEndPrice());
        verify(cartProductRepository).save(cartProduct);
        verify(endPriceCalculator).calculateCartProductEndPrice(cartProduct);
        verifyNoMoreInteractions(cartProductRepository, endPriceCalculator, cartProductRepository);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setCategory(Category.DRINKS.getValue());
        product.setDiscount(BigDecimal.valueOf(50));
        product.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        product.setEndPrice(BigDecimal.valueOf(30));
        return product;
    }

    private CartProduct getCartProduct() {
        CartProduct product = new CartProduct();
        product.setId(1L);
        product.setName("TestProduct");
        product.setCategory(Category.DRINKS.getValue());
        product.setDiscount(BigDecimal.valueOf(50));
        product.setPriceBeforeDiscount(BigDecimal.valueOf(60));
        product.setEndPrice(BigDecimal.valueOf(30));
        product.setQuantity(3);
        return product;
    }

    private ShoppingCart getShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName("TestCart");
        shoppingCart.setId(2L);
        Set<CartProduct> cartProducts = new HashSet<>();
        shoppingCart.setProducts(cartProducts);
        return shoppingCart;
    }
}