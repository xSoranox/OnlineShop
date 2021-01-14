package com.onlineshop.enumeration;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnumerableCategoryTest {

    @Test
    public void isEqualTo() {
        assertFalse(Category.DRINKS.isEqualTo(Category.DAIRY_AND_EGGS.getValue()));
        assertTrue(Category.DRINKS.isEqualTo(Category.DRINKS.getValue()));
    }
}