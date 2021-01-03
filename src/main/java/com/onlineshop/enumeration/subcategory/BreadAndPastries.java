package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;
import lombok.Getter;

public enum BreadAndPastries implements EnumerableSubcategory {

    BREAD("Bread"),
    CONFECTIONERY("Confectionery");

    @Getter
    private String value;

    @Getter
    private Category category;

    BreadAndPastries(String value) {
        this.value = value;
        this.category = Category.BREAD_AND_PASTRIES;
    }

}
