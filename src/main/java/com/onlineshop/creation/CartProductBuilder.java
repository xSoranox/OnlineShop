package com.onlineshop.creation;

import java.math.BigDecimal;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.ShoppingCart;

public class CartProductBuilder {
	
    private String name;
    private String category;
    private BigDecimal priceBeforeDiscount;
    private BigDecimal discount;
    private BigDecimal endPrice;
    private ShoppingCart shoppingCart;
    private Long originalProductId;
    private int quantity;

    public CartProductBuilder(String name, String category, BigDecimal priceBeforeDiscount, 
    		ShoppingCart shoppingCart, Long originalProductId) {
        this.name = name;
        this.category = category;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.shoppingCart = shoppingCart;
        this.originalProductId = originalProductId;
        this.quantity = 1;
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

    public Long getOriginalProductId() {
        return originalProductId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public CartProduct build() {
        return new CartProduct(this);
    }

}
