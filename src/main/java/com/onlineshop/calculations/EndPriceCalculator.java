package com.onlineshop.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.Product;
import com.onlineshop.domain.ProductInterface;

import org.springframework.stereotype.Component;

@Component
public class EndPriceCalculator {

	public BigDecimal calculateOriginalProductEndPrice(Product product) {
		return calculatePriceAfterDiscount(product);
	}

	public BigDecimal calculateCartProductEndPrice(CartProduct cartProduct) {
		BigDecimal priceAfterDiscount = calculatePriceAfterDiscount(cartProduct);
		return priceAfterDiscount.multiply(BigDecimal.valueOf(cartProduct.getQuantity()));
	}
	
	public BigDecimal calculateTotalSum(List<CartProduct> cartProducts) {
		BigDecimal totalSum = cartProducts.stream()
			.map(cp -> cp.getEndPrice())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		return totalSum;
	}

	private BigDecimal calculatePriceAfterDiscount(ProductInterface product) {
		BigDecimal discount = product.getDiscount();
		BigDecimal priceBeforeDiscount = product.getPriceBeforeDiscount();

		if (discount == null || discount.equals(BigDecimal.ZERO)) {
			return product.getPriceBeforeDiscount();
		}

		BigDecimal amount = priceBeforeDiscount.divide(BigDecimal.valueOf(100)).multiply(discount)
				.setScale(2, RoundingMode.HALF_EVEN);
		return priceBeforeDiscount.subtract(amount);
	}
}
