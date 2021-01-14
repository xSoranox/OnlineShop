package com.onlineshop.domain;

import java.math.BigDecimal;

public interface ProductInterface {
	
    Long id = null;
    String name = null;
    String category = null;
    BigDecimal priceBeforeDiscount = null;
    BigDecimal discount = null;
    BigDecimal endPrice = null;
    
    BigDecimal getDiscount();
    BigDecimal getPriceBeforeDiscount();
}
