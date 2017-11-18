package com.shopping_cart;

import java.util.ArrayList;

public class PricingRules {
	public ArrayList<Item> apply(ArrayList<Item> items){
		if (items == null || items.isEmpty())
			throw new RuntimeException("Invalid number of items");
		return null;
	}
}
