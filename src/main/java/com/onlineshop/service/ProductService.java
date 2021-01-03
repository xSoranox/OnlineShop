package com.onlineshop.service;

import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.onlineshop.domain.Product;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductExampleCreator productCreator;

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Set<Product> getExampleOfProducts() {
        return productCreator.getExampleOfProducts();
    }

    public List<Product> findProductsByName(String productName) {
        return repository.findProductsByName(productName);
    }

}
