package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;
import lombok.Getter;

public enum SweetsAndSnacks implements EnumerableSubcategory {

    SWEETS("Sweets"),
    CHOCOLATE("Chocolate"),
    WAFFLES("Waffles"),
    CHIPS("Chips"),
    POPCORN_AND_SNACKS("Popcorn and snacks"),
    NUTS("Nuts"),
    BISCUITS("Biscuits");

    @Getter
    private String value;

    @Getter
    private Category category;

    SweetsAndSnacks(String value) {
        this.value = value;
        this.category = Category.SWEETS_AND_SNACKS;
    }

}
