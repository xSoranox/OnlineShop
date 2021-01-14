package com.onlineshop.domain;

import java.math.BigDecimal;

public interface ProductInterface {
	
    public Long id = null;
    public String name = null;
    public String category = null;
    public BigDecimal priceBeforeDiscount = null;
    public BigDecimal discount = null;
    public BigDecimal endPrice = null;
    
    BigDecimal getDiscount();
    BigDecimal getPriceBeforeDiscount();
}
