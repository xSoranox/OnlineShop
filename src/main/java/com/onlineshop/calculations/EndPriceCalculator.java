package com.onlineshop.calculations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.onlineshop.domain.Product;

public class EndPriceCalculator {

    public BigDecimal calculateEndPrice(Product product) {
        BigDecimal discount = product.getDiscount();
        BigDecimal priceBeforeDiscount = product.getPriceBeforeDiscount();

        if (discount == null || discount.equals(BigDecimal.ZERO)) {
            return product.getPriceBeforeDiscount();
        }

        BigDecimal amount = priceBeforeDiscount.divide(BigDecimal.valueOf(100)).multiply(discount).setScale(2, RoundingMode.HALF_EVEN);
        return priceBeforeDiscount.subtract(amount);
    }

}
