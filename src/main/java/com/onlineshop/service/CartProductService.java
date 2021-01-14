package com.onlineshop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.creation.CartProductBuilder;
import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ShoppingCart;
import com.onlineshop.repository.CartProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartProductService {
	
	@Autowired
	private CartProductRepository cartProductRepository;
	@Autowired
	private EndPriceCalculator endPriceCalculator;
	
	public CartProduct createCartProduct(Product product, ShoppingCart cart) {
		CartProduct cartProduct = new CartProductBuilder(product.getName(), product.getCategory(), 
				product.getPriceBeforeDiscount(), cart, product.getId())
				.setDiscount(product.getDiscount())
				.setEndPrice(product.getEndPrice())
				.build();
		cart.getProducts().add(cartProduct);

		CartProduct createdCartProduct = cartProductRepository.save(cartProduct);
		return createdCartProduct;
	}
	
	@Transactional
	public void updateQuantityWhenAddingExistingCartProduct(CartProduct cartProduct) {
		cartProduct.setQuantity(cartProduct.getQuantity() + 1);
		cartProduct.setEndPrice(endPriceCalculator.calculateCartProductEndPrice(cartProduct));
		cartProductRepository.save(cartProduct);
	}
	
	@Transactional
	public void updateQuantityWhenDeleteCartProduct(CartProduct cartProduct, int productQuantity) {
		cartProduct.setQuantity(productQuantity - 1);
		cartProduct.setEndPrice(endPriceCalculator.calculateCartProductEndPrice(cartProduct));
		cartProductRepository.save(cartProduct);
	}
}
