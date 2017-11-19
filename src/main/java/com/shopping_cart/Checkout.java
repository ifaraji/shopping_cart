package com.shopping_cart;

import java.util.ArrayList;

public class Checkout {
	private ArrayList<Item> items;
	private PricingRules pricingRules;
	
	public Checkout(PricingRules pricingRules){
		this.pricingRules = pricingRules;
		items = new ArrayList<Item>();
	}
	
	public void scan(Item item){
		items.add(item);
	}
	
	public double total(){
		double finalPrice = 0;
		for (Item item : getItems())
			finalPrice += item.getPrice();
		return finalPrice;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
}
