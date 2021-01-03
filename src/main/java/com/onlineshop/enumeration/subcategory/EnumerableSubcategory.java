package com.onlineshop.enumeration.subcategory;

import com.onlineshop.enumeration.Category;

public interface EnumerableSubcategory {

    String getValue();

    Category getCategory();

    default boolean isEqualTo(String value) {
        return getValue().equalsIgnoreCase(value);
    }

}