package com.onlineshop.service;

import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.Product;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
	
	public List<Product> findProductsByType(String productType) {
		String category = productTypeService.getCategoryByType(productType);
		return repository.getProductsByCategory(category);
	}

}
