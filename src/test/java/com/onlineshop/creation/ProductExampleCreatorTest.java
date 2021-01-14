package com.onlineshop.creation;

import com.onlineshop.calculations.EndPriceCalculator;
import com.onlineshop.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductExampleCreatorTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private EndPriceCalculator endPriceCalculator;
    @InjectMocks
    private ProductExampleCreator productExampleCreator;

    @Test
    public void testCreateExampleOfProducts() {
        when(endPriceCalculator.calculateOriginalProductEndPrice(any())).thenReturn(BigDecimal.valueOf(10));
        productExampleCreator.createExampleOfProducts();

        verify(endPriceCalculator, times(5)).calculateOriginalProductEndPrice(any());
        verify(repository, times(5)).save(any());
        verifyNoMoreInteractions(repository, endPriceCalculator);
    }
}