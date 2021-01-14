package com.onlineshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ProductTypeServiceTest {

    ProductTypeService productTypeService = new ProductTypeService();

    @Test
    public void testAllTypes() {
        String result1 = productTypeService.getCategoryByType("breadAndPastries");
        String result2 = productTypeService.getCategoryByType("dairyAndEggs");
        String result3 = productTypeService.getCategoryByType("drinks");
        String result4 = productTypeService.getCategoryByType("fruitAndVegetables");
        String result5 = productTypeService.getCategoryByType("meatAndFish");
        String result6 = productTypeService.getCategoryByType("sweetsAndSnacks");

        assertEquals(result1, "Bread and pastries");
        assertEquals(result2, "Dairy and eggs");
        assertEquals(result3, "Drinks");
        assertEquals(result4, "Fruit and vegetables");
        assertEquals(result5, "Meat and fish");
        assertEquals(result6, "Sweets and snacks");
    }

    @Test
    public void testException() {
        try {
            productTypeService.getCategoryByType("other");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo(null);
        }
    }
}