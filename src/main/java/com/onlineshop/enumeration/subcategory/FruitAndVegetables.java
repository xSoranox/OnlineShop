package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;

import lombok.Getter;

public enum FruitAndVegetables implements EnumerableSubcategory {

    VEGETABLES("Vegetables"),
    FRUIT("Fruit"),
    MUSHROOMS("Mushrooms");

    @Getter
    private String value;

    @Getter
    private Category category;

    FruitAndVegetables(String value) {
        this.value = value;
        this.category = Category.FRUIT_AND_VEGETABLES;
    }
}
