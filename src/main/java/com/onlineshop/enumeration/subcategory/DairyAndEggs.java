package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;
import lombok.Getter;
public enum DairyAndEggs implements EnumerableSubcategory {

    MILK("Milk"),
    KEFIR("Kefir and sour milk products"),
    CHEESE("Cheese"),
    EGGS("Eggs"),
    BUTTER("Butter and margarine"),
    YOGURT_AND_DESSERTS("Yogurt and desserts");

    @Getter
    private String value;

    @Getter
    private Category category;

    DairyAndEggs(String value) {
        this.value = value;
        this.category = Category.DAIRY_AND_EGGS;
    }

}
