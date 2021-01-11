package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.Product;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
	public List<Product> findProductsByType(String productType) {
		String category = productTypeService.getCategoryByType(productType);
		return repository.getProductsByCategory(category);
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
	
	@Transactional
	public void saveNewProduct(Product product) {
		String category = productTypeService.getCategoryByType(product.getCategory());
		product.setCategory(category);
		BigDecimal endPrice = endPriceCalculator.calculateEndPrice(product);
		product.setEndPrice(endPrice);
		repository.save(product);
	}
	
	public Product createProduct() {
		return new Product();
	}

}
