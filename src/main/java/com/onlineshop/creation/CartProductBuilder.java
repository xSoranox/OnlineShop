package com.onlineshop.creation;

import java.math.BigDecimal;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.ShoppingCart;

public class CartProductBuilder {
	
    private String name;
    private String category;
    private String subcategory;
    private BigDecimal priceBeforeDiscount;
    private BigDecimal discount;
    private BigDecimal endPrice;
    private ShoppingCart shoppingCart;

    public CartProductBuilder(String name, String category, String subcategory, BigDecimal priceBeforeDiscount, ShoppingCart shoppingCart) {
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.shoppingCart = shoppingCart;
    }

    public CartProductBuilder setDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }
    
    public CartProductBuilder setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public BigDecimal getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
    
    public BigDecimal getEndPrice() {
        return endPrice;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public CartProduct build() {
        return new CartProduct(this);
    }

}
