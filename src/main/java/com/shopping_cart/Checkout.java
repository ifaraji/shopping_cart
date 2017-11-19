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
		
	}
	
	public double total(){
		return 0;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
}
