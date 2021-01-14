package com.onlineshop.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.onlineshop.creation.CartProductBuilder;

import lombok.Data;

@Data
@Entity
@Table(name = "CartProducts")
public class CartProduct implements ProductInterface {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priceBeforeDiscount, category, discount, endPrice);
    } 


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartProduct other = (CartProduct) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (endPrice == null) {
			if (other.endPrice != null)
				return false;
		} else if (!endPrice.equals(other.endPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (originalProductId == null) {
			if (other.originalProductId != null)
				return false;
		} else if (!originalProductId.equals(other.originalProductId))
			return false;
		if (priceBeforeDiscount == null) {
			if (other.priceBeforeDiscount != null)
				return false;
		} else if (!priceBeforeDiscount.equals(other.priceBeforeDiscount))
			return false;
		if (quantity != other.quantity)
			return false;
		if (shoppingCart == null) {
			if (other.shoppingCart != null)
				return false;
		} else if (!shoppingCart.equals(other.shoppingCart))
			return false;
		return true;
	}
}
