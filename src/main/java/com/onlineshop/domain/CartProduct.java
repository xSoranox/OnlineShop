package com.onlineshop.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.onlineshop.creation.CartProductBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CartProducts")
public class CartProduct implements ProductInterface {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CartID", nullable = false)
    private ShoppingCart shoppingCart;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category")
    private String category;

    @Column(name = "PriceBeforeDiscount")
    private BigDecimal priceBeforeDiscount;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "EndPrice")
    private BigDecimal endPrice;
    
    @Column(name = "OriginalProductId")
    private Long originalProductId;
    
    @Column(name = "Quantity")
    private int quantity;
    
    public CartProduct() {
    	
    }

    public CartProduct(CartProductBuilder builder) {
        this.name = builder.getName();
        this.category = builder.getCategory();
        this.priceBeforeDiscount = builder.getPriceBeforeDiscount();
        this.discount = builder.getDiscount();
        this.endPrice = builder.getEndPrice();
        this.shoppingCart = builder.getShoppingCart();
        this.originalProductId = builder.getOriginalProductId();
        this.quantity = builder.getQuantity();
    }
}
