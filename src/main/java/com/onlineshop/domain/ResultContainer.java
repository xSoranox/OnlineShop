package com.onlineshop.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Structural GOF pattern: Composite
 * Object composes two other objects so that they can be manipulated as one object
 */
@Getter
@Setter
public class ResultContainer {
	
	private List<Product> products;
	private String message;

}
