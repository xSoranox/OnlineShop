package com.onlineshop.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "ShoppingCarts")
public class ShoppingCart {

    @Id
    @Column(name = "CartID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "shoppingCart")
    private Set<CartProduct> products;

    @Column(name = "Name")
    private String name;

    public BigDecimal getTotalCost() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartProduct product : products) {
            totalPrice = totalPrice.add(product.getEndPrice());
        }
        return totalPrice;
    }
}
