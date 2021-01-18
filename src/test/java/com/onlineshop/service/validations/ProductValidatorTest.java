package com.onlineshop.service.validations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.onlineshop.domain.Product;
import com.onlineshop.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidatorTest {
	
    @Mock
	private ProductService productService;
    @InjectMocks
    private ProductValidator productValidator;
    
    @Test
    public void testValidateAndGetMessageProductName() {
    	Product product = new Product();
    	Product product2 = new Product();
    	product2.setName("");
    	String result1 = productValidator.validateAndGetMessage(product, false);
    	String result2 = productValidator.validateAndGetMessage(product2, false);
    	assertEquals(result1, "The product name must not be empty");
    	assertEquals(result2, "The product name must not be empty");
    	verifyNoMoreInteractions(productService);
    }
    
    @Test
    public void testValidateAndGetMessageCategory() {
    	Product product = new Product();
    	product.setName("testName");
    	String result = productValidator.validateAndGetMessage(product, false);
    	assertEquals(result, "Please select category");
    	verifyNoMoreInteractions(productService);
    }
    
    @Test
    public void testValidateAndGetMessageInitialPrice() {
    	Product product = new Product();
    	product.setName("testName");
    	product.setCategory("category");
    	String result = productValidator.validateAndGetMessage(product, false);
    	assertEquals(result, "Initial price has to be set");
    	verifyNoMoreInteractions(productService);
    }
    
    @Test
    public void testValidateAndGetMessageExistsNameInDatabase() {
    	Product product = new Product();
    	product.setName("testName");
    	product.setCategory("category");
    	product.setPriceBeforeDiscount(BigDecimal.valueOf(5));
    	List<Product> products = new ArrayList<>();
    	Product testProduct = new Product();
    	testProduct.setName("testName");
    	products.add(testProduct);
        when(productService.findAllProducts()).thenReturn(products);
    	String result = productValidator.validateAndGetMessage(product, true);
    	assertEquals(result, "The given name already exists");
    	verify(productService).findAllProducts();
    	verifyNoMoreInteractions(productService);
    }
    
    @Test
    public void testValidateAndGetMessageExistsNameInDatabaseNonCreationalFlow() {
    	Product product = new Product();
    	product.setName("testName");
    	product.setCategory("category");
    	product.setPriceBeforeDiscount(BigDecimal.valueOf(5));
    	List<Product> products = new ArrayList<>();
    	Product testProduct = new Product();
    	testProduct.setName("testName");
    	products.add(testProduct);
    	String result = productValidator.validateAndGetMessage(product, false);
    	assertEquals(result, "");
    	verifyNoMoreInteractions(productService);
    }
    
    @Test
    public void testValidateAndGetMessageWithoutIssues() {
    	Product product = new Product();
    	product.setName("testName");
    	product.setCategory("category");
    	product.setPriceBeforeDiscount(BigDecimal.valueOf(5));
    	String result = productValidator.validateAndGetMessage(product, false);
    	assertEquals(result, "");
    	verifyNoMoreInteractions(productService);
    }

}
