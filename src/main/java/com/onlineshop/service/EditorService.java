package com.onlineshop.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.Product;
import com.onlineshop.repository.EditorProductRepository;
import com.onlineshop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditorService {
	
	@Autowired
	private EditorProductRepository editorProductRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private EndPriceCalculator endPriceCalculator;
	
	public List<Product> findAllProducts() {
		return editorProductRepository.findAllEditorProducts();
	}
	
	public void addProductToEditor(Long productId) {
		editorProductRepository.addProductToEditor(productId);
	}
	
	public void setDiscountToEditorProducts(String stringDiscount) {
		BigDecimal discount = new BigDecimal(stringDiscount);
		List<Product> editorProducts = findAllProducts();
		editorProducts.forEach(p -> p.setDiscount(discount));
		editorProducts.forEach(p -> updateEndPrice(p));
	}
	
	public void deleteAllProductsFromEditor() {
		editorProductRepository.flushEditor();
	}
	
	public void deleteProductFromEditor(Long productId) {
		editorProductRepository.removeProductFromEditor(productId);
	}
	
	private void updateEndPrice(Product product) {
		BigDecimal endPrice = endPriceCalculator.calculateOriginalProductEndPrice(product);
		product.setEndPrice(endPrice);
		productRepository.save(product);
	}

}
