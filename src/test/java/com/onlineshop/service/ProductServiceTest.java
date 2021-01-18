package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.creation.ProductExampleCreator;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ResultContainer;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.service.validations.ProductValidator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductExampleCreator productCreator;
    @Mock
    private ProductTypeService productTypeService;
    @Mock
    private EndPriceCalculator endPriceCalculator;
    @Mock
    private ProductValidator productValidator;
    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindAllProducts() {
        List<Product> products = getListOfProducts();
        when(repository.findAll()).thenReturn(products);
        List<Product> result = productService.findAllProducts();

        assertFalse(result.isEmpty());
        Assert.assertEquals(result, products);
        assertEquals(result.get(0).getName(), "TestProduct");
        assertEquals(result.size(), 1);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testCreateExampleOfProducts() {
        productService.createExampleOfProducts();
        verify(productCreator).createExampleOfProducts();
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testFindProductsByName() {
        when(repository.findProductsByName("productName")).thenReturn(getListOfProducts());
        List<Product> result = productService.findProductsByName("productName");

        assertFalse(result.isEmpty());
        assertEquals(result.get(0).getName(), "TestProduct");
        assertEquals(result.size(), 1);
        verify(repository).findProductsByName("productName");
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setName("TestProduct");
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> result = productService.findProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(result.get().getName(), "TestProduct");
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testFindProductsById() {
        Product product = new Product();
        product.setName("TestProduct");
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        List<Product> result = productService.findProductsById("1");

        assertFalse(result.isEmpty());
        assertEquals(result.get(0).getName(), "TestProduct");
        assertEquals(result.size(), 1);
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);

    }

    @Test
    public void testFindProductsByType() {
        when(productTypeService.getCategoryByType("type")).thenReturn("category");
        when(repository.getProductsByCategory("category")).thenReturn(getListOfProducts());
        ResultContainer result = productService.findProductsByType("type");

        assertFalse(result.getProducts().isEmpty());
        assertEquals(result.getProducts().get(0).getName(), "TestProduct");
        assertEquals(result.getProducts().size(), 1);
        assertEquals(result.getMessage(), "");
        verify(productTypeService).getCategoryByType("type");
        verify(repository).getProductsByCategory("category");
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }
    
    @Test
    public void testFindProductsByTypeMessage() {
        when(productTypeService.getCategoryByType("type")).thenReturn("");
        ResultContainer result = productService.findProductsByType("type");

        assertTrue(result.getProducts().isEmpty());
        assertEquals(result.getMessage(), "Please select category");
        verify(productTypeService).getCategoryByType("type");
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testDeleteAllProducts() {
        productService.deleteAllProducts();
        verify(repository).deleteAll();
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testDeleteProductById() {
        productService.deleteProductById(1L);
        verify(repository).deleteProductById(1L);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        productService.saveProduct(product);
        verify(repository).save(product);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testSaveNewProductStandardFlow() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setCategory("Category");
        when(productTypeService.getCategoryByType("Category")).thenReturn("NewCategory");
        when(endPriceCalculator.calculateOriginalProductEndPrice(product)).thenReturn(BigDecimal.valueOf(10));
        when(productValidator.validateAndGetMessage(product, false)).thenReturn("");
        String result = productService.saveNewProduct(product, false);

        assertEquals(product.getEndPrice(), BigDecimal.valueOf(10));
        assertEquals(product.getCategory(), "NewCategory");
        assertEquals(product.getDiscount(), BigDecimal.ZERO);
        assertEquals(result, "");
        verify(productTypeService).getCategoryByType("Category");
        verify(endPriceCalculator).calculateOriginalProductEndPrice(product);
        verify(repository).save(product);
        verify(productValidator).validateAndGetMessage(product, false);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }
    
    @Test
    public void testSaveNewProductEmptyStringFlow() {
        Product product = new Product();
        product.setName("TestProduct");
        when(productValidator.validateAndGetMessage(product, false)).thenReturn("Select category");
        String result = productService.saveNewProduct(product, false);

        assertEquals(result, "Select category");
        verify(productValidator).validateAndGetMessage(product, false);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }
    
    @Test
    public void testSaveNewProductSecondEmptyStringFlow() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setCategory("Category");
        when(productTypeService.getCategoryByType("Category")).thenReturn("");
        when(productValidator.validateAndGetMessage(product, false)).thenReturn("");
        String result = productService.saveNewProduct(product, false);

        assertEquals(product.getCategory(), "Category");
        assertEquals(result, "Please select category");
        verify(productTypeService).getCategoryByType("Category");
        verify(productValidator).validateAndGetMessage(product, false);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }
    
    @Test
    public void testSaveNewProductNormalDiscount() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setCategory("Category");
        product.setDiscount(BigDecimal.TEN);
        when(productTypeService.getCategoryByType("Category")).thenReturn("NewCategory");
        when(endPriceCalculator.calculateOriginalProductEndPrice(product)).thenReturn(BigDecimal.valueOf(10));
        when(productValidator.validateAndGetMessage(product, false)).thenReturn("");
        String result = productService.saveNewProduct(product, false);

        assertEquals(product.getEndPrice(), BigDecimal.valueOf(10));
        assertEquals(product.getCategory(), "NewCategory");
        assertEquals(product.getDiscount(), BigDecimal.TEN);
        assertEquals(result, "");
        verify(productTypeService).getCategoryByType("Category");
        verify(endPriceCalculator).calculateOriginalProductEndPrice(product);
        verify(repository).save(product);
        verify(productValidator).validateAndGetMessage(product, false);
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    @Test
    public void testCreateProduct() {
        Product product = productService.createProduct();
        assertThat(product).hasAllNullFieldsOrPropertiesExcept("isInEditor");
        verifyNoMoreInteractions(repository, productCreator, productTypeService, endPriceCalculator);
    }

    private List<Product> getListOfProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("TestProduct");
        products.add(product);
        return products;
    }
}