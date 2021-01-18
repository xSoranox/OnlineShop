package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.service.validations.ProductValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.Product;
import com.onlineshop.domain.ResultContainer;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ProductExampleCreator productCreator;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private EndPriceCalculator endPriceCalculator;
	@Autowired
	private ProductValidator productValidator;

	public List<Product> findAllProducts() {
		return repository.findAll();
	}

	public void createExampleOfProducts() {
		productCreator.createExampleOfProducts();
	}

	public List<Product> findProductsByName(String productName) {
		return repository.findProductsByName(productName);
	}
	
	public Optional<Product> findProductById(Long productId) {
		return repository.findById(productId);
	}
	
	public List<Product> findProductsById(String productId) {
		Long id = Long.parseLong(productId);
		Optional<Product> product = findProductById(id);
		List<Product> products = new ArrayList<>();
		product.ifPresent(p -> products.add(p));
		return products;
	}
	
	public ResultContainer findProductsByType(String productType) {
		String category = productTypeService.getCategoryByType(productType);
		if (category.equals("")) {
			return getResult("Please select category", Collections.emptyList());
		}
		return getResult("", repository.getProductsByCategory(category));
	}
	
	public void deleteAllProducts() {
		repository.deleteAll();
	}
	
	public void deleteProductById(Long id) {
		repository.deleteProductById(id);
	}
	
	public void saveProduct(Product product) {
		repository.save(product);
	}
	
	/**
	 * Structural GOF pattern: Facade
	 * Combines the functionality of two classes into one common
	 */
	@Transactional
	public String saveNewProduct(Product product, boolean isCreational) {
		String message = productValidator.validateAndGetMessage(product, isCreational);
		if (!message.equals("")) {
			return message;
		}
		String category = productTypeService.getCategoryByType(product.getCategory());
		if (category.equals("")) {
			return "Please select category";
		}
		product.setCategory(category);
		BigDecimal endPrice = endPriceCalculator.calculateOriginalProductEndPrice(product);
		product.setEndPrice(endPrice);
		if (product.getDiscount() == null) {
			product.setDiscount(BigDecimal.ZERO);
		}
		repository.save(product);
		return "";
	}
	
	public Product createProduct() {
		return new Product();
	}
	
	private ResultContainer getResult(String message, List<Product> products) {
		ResultContainer result = new ResultContainer();
		result.setProducts(products);
		result.setMessage(message);
		return result;
	}

}
