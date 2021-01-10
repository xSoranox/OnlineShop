package com.onlineshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ShoppingCart;
import com.onlineshop.enumeration.Category;
import com.onlineshop.enumeration.subcategory.BreadAndPastries;
import com.onlineshop.repository.CartProductRepository;
import com.onlineshop.repository.ShoppingCartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	CartProductRepository cartProductRepository;
	@Autowired
	CartProductService cartProductService;
	@Autowired
	ProductService productService;

	public List<CartProduct> findAllCartProducts(Long id) {
		return cartProductRepository.findProductsByCartId(id);
	}

	public void deleteAllCartProducts(Long id) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(id);
		cart.ifPresent(c -> cartProductRepository.deleteAllCartProductsByCartId(c));
	}
	
	public void deleteCartProduct(Long cartId, Long productId) {
		Optional<CartProduct> cartProduct = cartProductRepository.findById(productId);
		cartProduct.ifPresent(c -> validateCartAndRemoveProduct(c, cartId));
	}
	
	public void addProductToCart(Long cartId, Long productId) {
		Optional<Product> product = productService.findProductById(productId);
		product.ifPresent(p -> validateCartAndAddProduct(p, cartId));
	}
	
	private void validateCartAndAddProduct(Product product, Long cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		cart.ifPresent(c -> createCartProductAndAddItToCart(c, product));
	}
	
	private void createCartProductAndAddItToCart(ShoppingCart cart, Product product) {
		CartProduct cartProduct = cartProductService.createCartProduct(product, cart);
		cart.getProducts().add(cartProduct);
		shoppingCartRepository.save(cart);
	}
	
	private void validateCartAndRemoveProduct(CartProduct cartProduct, Long cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		cart.ifPresent(c -> removeProductFromCart(c, cartProduct));
	}
	
	private void removeProductFromCart(ShoppingCart shoppingCart, CartProduct cartProduct) {
		shoppingCart.getProducts().remove(cartProduct);
		shoppingCartRepository.save(shoppingCart);
		cartProductRepository.deleteProductFromCart(cartProduct);
	}
}
