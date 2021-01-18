package com.onlineshop.service.validations;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineshop.domain.Product;
import com.onlineshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductValidator {
	
	@Autowired
	private ProductService productService;
	
	public String validateAndGetMessage(Product product, boolean isCreational) {
		
		String productName = product.getName();
		
		if (productName == null || productName.isEmpty()) {
			return "The product name must not be empty";
		}
		if (product.getCategory() == null) {
			return "Please select category";
		}
		if (product.getPriceBeforeDiscount() == null || 
				product.getPriceBeforeDiscount().equals(BigDecimal.ZERO)) {
			return "Initial price has to be set";
		}
		if (isCreational && existsNameInDatabase(productName)) {
			return "The given name already exists";
		}
		
		return "";
	}
	
	private boolean existsNameInDatabase(String productName) {
		return productService.findAllProducts().stream().anyMatch(p -> p.getName().equals(productName));
	}

}
