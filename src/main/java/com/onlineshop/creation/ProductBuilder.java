package com.onlineshop.creation;

import java.math.BigDecimal;

import com.onlineshop.domain.Product;

public class ProductBuilder {

    private String name;
    private String category;
    private BigDecimal priceBeforeDiscount;
    private BigDecimal discount;

    public ProductBuilder(String name, String category, BigDecimal priceBeforeDiscount) {
        this.name = name;
        this.category = category;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public ProductBuilder setDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Product build() {
        return new Product(this);
    }

}
