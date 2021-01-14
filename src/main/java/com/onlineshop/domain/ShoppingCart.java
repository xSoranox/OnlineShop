package com.onlineshop.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
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

    public void addProduct(CartProduct product) {
        products.add(product);
    }

    public void totalNumberOfProducts() {
        products.size();
    }

    public BigDecimal getTotalCost() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartProduct product : products) {
            totalPrice = totalPrice.add(product.getEndPrice());
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, products);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", products=" + products +
                '}';
    }
}
