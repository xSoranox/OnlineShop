package com.onlineshop.service;

import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.Product;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ProductExampleCreator productCreator;
	@Autowired
	private ProductTypeService productTypeService;

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

}
