package com.shopping_cart;

import java.util.List;

public class PricingRules {
	public List<Item> apply(List<Item> items){
		if (items == null || items.isEmpty())
			throw new RuntimeException("Invalid number of items");
		return null;
	}
}
