package com.onlineshop.enumeration;

import lombok.Getter;

public enum Category implements EnumerableCategory {

    FRUIT_AND_VEGETABLES("Fruit and vegetables"),
    DAIRY_AND_EGGS("Dairy and eggs"),
    MEAT_AND_FISH("Meat and fish"),
    BREAD_AND_PASTRIES("Bread and pastries"),
    SWEETS_AND_SNACKS("Sweets and snacks"),
    DRINKS("Drinks");

    @Getter
    private String value;

    Category(String value) {
        this.value = value;
    }
}
