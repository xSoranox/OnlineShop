package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;
import lombok.Getter;

public enum MeatAndFish implements EnumerableSubcategory {

    FRESH_MEAT("Fresh meat"),
    FRESH_FISH("Fresh fish"),
    MARINATED_MEAT("Marinated meat"),
    SEAFOOD("Seafood"),
    SAUSAGES_AND_PATES("Sausages and pates"),
    CURED_MEAT("Cured meat"),
    CURED_FISH("Cured fish"),
    PRESERVES("Preserves");

    @Getter
    private String value;

    @Getter
    private Category category;

    MeatAndFish(String value) {
        this.value = value;
        this.category = Category.MEAT_AND_FISH;
    }
}
