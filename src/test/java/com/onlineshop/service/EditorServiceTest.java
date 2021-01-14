package com.onlineshop.service;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.domain.Product;
import com.onlineshop.repository.EditorProductRepository;
import com.onlineshop.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EditorServiceTest {

    @Mock
    private EditorProductRepository editorProductRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private EndPriceCalculator endPriceCalculator;
    @InjectMocks
    private EditorService editorService;

    @Test
    public void findAllProducts() {
        when(editorProductRepository.findAllEditorProducts()).thenReturn(getListOfProducts());
        List<Product> result = editorService.findAllProducts();
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "TestProduct");
        verify(editorProductRepository).findAllEditorProducts();
        verifyNoMoreInteractions(editorProductRepository, productRepository, endPriceCalculator);
    }

    @Test
    public void addProductToEditor() {
        editorService.addProductToEditor(1L);
        verify(editorProductRepository).addProductToEditor(1L);
        verifyNoMoreInteractions(editorProductRepository, productRepository, endPriceCalculator);
    }

    @Test
    public void setDiscountToEditorProducts() {
        List<Product> products = getListOfProducts();
        Product product = products.get(0);
        when(editorProductRepository.findAllEditorProducts()).thenReturn(products);
        when(endPriceCalculator.calculateOriginalProductEndPrice(products.get(0))).thenReturn(BigDecimal.valueOf(30));
        editorService.setDiscountToEditorProducts("50");

        assertEquals(products.get(0).getDiscount(), BigDecimal.valueOf(50));
        assertEquals(products.get(0).getEndPrice(), BigDecimal.valueOf(30));
        verify(productRepository).save(product);
        verify(editorProductRepository).findAllEditorProducts();
        verify(endPriceCalculator).calculateOriginalProductEndPrice(product);
        verifyNoMoreInteractions(editorProductRepository, productRepository, endPriceCalculator);
    }

    @Test
    public void deleteAllProductsFromEditor() {
        editorService.deleteAllProductsFromEditor();
        verify(editorProductRepository).flushEditor();
        verifyNoMoreInteractions(editorProductRepository, productRepository, endPriceCalculator);
    }

    @Test
    public void deleteProductFromEditor() {
        editorService.deleteProductFromEditor(1L);
        verify(editorProductRepository).removeProductFromEditor(1L);
        verifyNoMoreInteractions(editorProductRepository, productRepository, endPriceCalculator);
    }

    private List<Product> getListOfProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("TestProduct");
        products.add(product);
        return products;
    }
}