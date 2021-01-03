package com.onlineshop.enumeration;

public interface EnumerableCategory {
    String getValue();

    default boolean isEqualTo(String value) {
        return getValue().equalsIgnoreCase(value);
    }
}