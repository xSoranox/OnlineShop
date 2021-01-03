package com.onlineshop.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CartProducts")
public class CartProduct {

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

    @Column(name = "Subcategory")
    private String subcategory;

    @Column(name = "PriceBeforeDiscount")
    private BigDecimal priceBeforeDiscount;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "EndPrice")
    private BigDecimal endPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct product = (CartProduct) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                category == product.category &&
                subcategory == product.subcategory &&
                Objects.equals(priceBeforeDiscount, product.priceBeforeDiscount) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(endPrice, product.endPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priceBeforeDiscount, category, discount, endPrice);
    }
}
