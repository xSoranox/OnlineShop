package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;

import lombok.Getter;

public enum Drinks implements EnumerableSubcategory {

    WATER("Water"),
    JUICES("Juices"),
    SOFT_DRINKS("Soft drinks"),
    SYRUPS("Syrups"),
    ENERGY_DRINKS("Energy drinks"),
    ALCOHOLIC("Alcoholic dinks");

    @Getter
    private String value;

    @Getter
    private Category category;

    Drinks(String value) {
        this.value = value;
        this.category = Category.DRINKS;
    }

}
