package com.onlineshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ShoppingCart;
import com.onlineshop.repository.CartProductRepository;
import com.onlineshop.repository.ShoppingCartRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private CartProductService cartProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private EndPriceCalculator endPriceCalculator;

	public List<CartProduct> findAllCartProducts(Long id) {
		return cartProductRepository.findProductsByCartId(id);
	}
	
	public BigDecimal calculateTotalSum(List<CartProduct> cartProducts) {
		return endPriceCalculator.calculateTotalSum(cartProducts);
	}
	
	public int getCartSize(Long id) {
		return findAllCartProducts(id).stream()
			.map(cp -> cp.getQuantity())
			.reduce(0, (a, b) -> a + b);
	}

	public void deleteAllCartProducts(Long id) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(id);
		cart.ifPresent(c -> cartProductRepository.deleteAllCartProductsByCart(c));
	}

	public void reduceCartProduct(Long cartId, Long productId, boolean shouldDelete) {
		Optional<CartProduct> cartProduct = cartProductRepository.findById(productId);
		cartProduct.ifPresent(c -> validateCartAndRemoveProduct(c, cartId, shouldDelete));
	}
	
	public void increaseProductQuantity(Long cartId, Long productId) {
		Optional<CartProduct> cartProduct = cartProductRepository.findById(productId);
		cartProduct.ifPresent(cp -> cartProductService.updateQuantityWhenAddingExistingCartProduct(cp));
	}

	public void addProductToCart(Long cartId, Long productId) {
		Optional<Product> product = productService.findProductById(productId);
		product.ifPresent(p -> validateCartAndAddProduct(p, cartId));
	}

	private void validateCartAndAddProduct(Product product, Long cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		cart.ifPresent(c -> validateExistingCartProducts(c, product));
	}

	private void validateExistingCartProducts(ShoppingCart cart, Product product) {
		Optional<CartProduct> cartProduct = cartProductRepository.returnsCartProductIfExists(product.getId());
		if (cartProduct.isPresent()) {
			cartProduct.ifPresent(cp -> cartProductService.updateQuantityWhenAddingExistingCartProduct(cp));
		} else {
			createNewCartProduct(cart, product);
		}
	}

	private void createNewCartProduct(ShoppingCart cart, Product product) {
		CartProduct cartProduct = cartProductService.createCartProduct(product, cart);
		cart.getProducts().add(cartProduct);
		shoppingCartRepository.save(cart);
	}

	private void validateCartAndRemoveProduct(CartProduct cartProduct, Long cartId, boolean shouldDelete) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		cart.ifPresent(c -> removeProductFromCart(c, cartProduct, shouldDelete));
	}

	@Transactional 
	private void removeProductFromCart(ShoppingCart shoppingCart, CartProduct cartProduct, 
			boolean shouldDelete) {
		int productQuantity = cartProduct.getQuantity();
		if (shouldDelete || productQuantity <= 1) {
			cartProductRepository.deleteProductFromCart(cartProduct);
		} else {
			cartProductService.updateQuantityWhenDeleteCartProduct(cartProduct, productQuantity);
		}
	}
}
