package com.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//		CartProduct cartProduct = new CartProductBuilder(product.getName(), product.getCategory(), product.getSubcategory(), 
//				product.getPriceBeforeDiscount(), cart)
//				.setDiscount(product.getDiscount())
//				.setEndPrice(product.getEndPrice())
//				.build();
		CartProduct cartProduct = new CartProduct();
		cartProduct.setName(product.getName());
		cartProduct.setCategory(product.getCategory());
		cartProduct.setSubcategory(product.getSubcategory());
		cartProduct.setPriceBeforeDiscount(product.getPriceBeforeDiscount());
		cartProduct.setDiscount(product.getDiscount());
		cartProduct.setEndPrice(product.getEndPrice());
		cartProduct.setShoppingCart(cart);
		cart.getProducts().add(cartProduct);

		CartProduct createdCartProduct = cartProductRepository.save(cartProduct);
		return createdCartProduct;
	}

}
