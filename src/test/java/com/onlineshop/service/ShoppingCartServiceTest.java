package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ShoppingCart;
import com.onlineshop.repository.CartProductRepository;
import com.onlineshop.repository.ShoppingCartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private CartProductService cartProductService;
    @Mock
    private ProductService productService;
    @Mock
    private EndPriceCalculator endPriceCalculator;
    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Test
    public void findAllCartProducts() {
        when(cartProductRepository.findProductsByCartId(1L)).thenReturn(getCartProducts());
        List<CartProduct> result = shoppingCartService.findAllCartProducts(1L);

        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "TestProduct");
        verify(cartProductRepository).findProductsByCartId(1L);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void calculateTotalSum() {
        List<CartProduct> cartProducts = getCartProducts();
        when(endPriceCalculator.calculateTotalSum(cartProducts)).thenReturn(BigDecimal.valueOf(10));
        BigDecimal result = shoppingCartService.calculateTotalSum(cartProducts);

        assertEquals(result, BigDecimal.valueOf(10));
        verify(endPriceCalculator).calculateTotalSum(cartProducts);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void getCartSize() {
        when(cartProductRepository.findProductsByCartId(1L)).thenReturn(getCartProducts());
        int result = shoppingCartService.getCartSize(1L);

        assertEquals(result, 3);
        verify(cartProductRepository).findProductsByCartId(1L);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void deleteAllCartProducts() {
        ShoppingCart shoppingCart = new ShoppingCart();
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        shoppingCartService.deleteAllCartProducts(1L);

        verify(shoppingCartRepository).findById(1L);
        verify(cartProductRepository).deleteAllCartProductsByCart(shoppingCart);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void reduceCartProductTrueFlow() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(3);
        when(cartProductRepository.findById(2L)).thenReturn(Optional.of(cartProduct));
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        shoppingCartService.reduceCartProduct(1L, 2L, true);

        verify(cartProductRepository).deleteProductFromCart(cartProduct);
        verify(cartProductRepository).findById(2L);
        verify(shoppingCartRepository).findById(1L);
        verifyZeroInteractions(cartProductService);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void reduceCartProductFalseFlowWithBigQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(3);
        when(cartProductRepository.findById(2L)).thenReturn(Optional.of(cartProduct));
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        shoppingCartService.reduceCartProduct(1L, 2L, false);

        verify(cartProductRepository).findById(2L);
        verify(shoppingCartRepository).findById(1L);
        verify(cartProductService).updateQuantityWhenDeleteCartProduct(cartProduct, 3);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);

    }

    @Test
    public void reduceCartProductFalseFlowWithSmallQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQuantity(1);
        when(cartProductRepository.findById(2L)).thenReturn(Optional.of(cartProduct));
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        shoppingCartService.reduceCartProduct(1L, 2L, false);

        verify(cartProductRepository).deleteProductFromCart(cartProduct);
        verify(cartProductRepository).findById(2L);
        verify(shoppingCartRepository).findById(1L);
        verifyZeroInteractions(cartProductService);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void increaseProductQuantity() {
        CartProduct cartProduct = new CartProduct();
        when(cartProductRepository.findById(2L)).thenReturn(Optional.of(cartProduct));
        shoppingCartService.increaseProductQuantity(1L, 2L);

        verify(cartProductService).updateQuantityWhenAddingExistingCartProduct(cartProduct);
        verify(cartProductRepository).findById(2L);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void testAddProductToCartFlowOfExistingCartProduct() {
        Product product = new Product();
        product.setId(2L);
        CartProduct cartProduct = new CartProduct();
        when(productService.findProductById(2L)).thenReturn(Optional.of(product));
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(new ShoppingCart()));
        when(cartProductRepository.returnsCartProductIfExists(2L)).thenReturn(Optional.of(cartProduct));
        shoppingCartService.addProductToCart(1L, 2L);

        verify(productService).findProductById(2L);
        verify(shoppingCartRepository).findById(1L);
        verify(cartProductRepository).returnsCartProductIfExists(2L);
        verify(cartProductService).updateQuantityWhenAddingExistingCartProduct(cartProduct);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    @Test
    public void testAddProductToCartFlowOfNoExistingCartProduct() {
        Product product = new Product();
        product.setId(3L);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(new HashSet<>());
        when(productService.findProductById(2L)).thenReturn(Optional.of(product));
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        shoppingCartService.addProductToCart(1L, 2L);

        assertEquals(shoppingCart.getProducts().size(), 1);
        assertFalse(shoppingCart.getProducts().isEmpty());
        verify(productService).findProductById(2L);
        verify(shoppingCartRepository).findById(1L);
        verify(cartProductRepository).returnsCartProductIfExists(3L);
        verify(cartProductService).createCartProduct(product, shoppingCart);
        verify(shoppingCartRepository).save(shoppingCart);
        verifyNoMoreInteractions(shoppingCartRepository, cartProductRepository, cartProductService,
                productService, endPriceCalculator);
    }

    private List<CartProduct> getCartProducts() {
        List<CartProduct> cartProducts = new ArrayList<>();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setName("TestProduct");
        cartProduct.setQuantity(3);
        cartProducts.add(cartProduct);
        return cartProducts;
    }
}