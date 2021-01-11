package com.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	CartProductRepository cartProductRepository;
	
	public CartProduct createCartProduct(Product product, ShoppingCart cart) {
		CartProduct cartProduct = new CartProductBuilder(product.getName(), product.getCategory(), 
				product.getPriceBeforeDiscount(), cart)
				.setDiscount(product.getDiscount())
				.setEndPrice(product.getEndPrice())
				.build();
		cart.getProducts().add(cartProduct);

		CartProduct createdCartProduct = cartProductRepository.save(cartProduct);
		return createdCartProduct;
	}

}
