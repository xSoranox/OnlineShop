package com.onlineshop.service;

import org.springframework.stereotype.Service;

import com.onlineshop.enumeration.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

	public String getCategoryByType(String type) {
		
		switch (type) {
		case "breadAndPastries":
			return Category.BREAD_AND_PASTRIES.getValue();
		case "dairyAndEggs":
			return Category.DAIRY_AND_EGGS.getValue();
		case "drinks":
			return Category.DRINKS.getValue();
		case "fruitAndVegetables":
			return Category.FRUIT_AND_VEGETABLES.getValue();
		case "meatAndFish":
			return Category.MEAT_AND_FISH.getValue();
		case "sweetsAndSnacks":
			return Category.SWEETS_AND_SNACKS.getValue();
		default:
			return "";
		}
	}
}
