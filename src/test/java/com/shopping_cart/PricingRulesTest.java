package com.shopping_cart;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.products.Products;

public class PricingRulesTest {

	@Test(expected = RuntimeException.class)
	public void whenEmptyListApplyThrowsException(){
		PricingRules pricingRules = new PricingRules();
		pricingRules.apply(new ArrayList<Item>());
	}
	
	@Test(expected = RuntimeException.class)
	public void whenListIsNullApplyThrowsException(){
		PricingRules pricingRules = new PricingRules();
		pricingRules.apply(null);
	}
	
	@Test
	public void whenNoDiscountIsAppliedItemsWontChange(){
		ArrayList<Item> items = new ArrayList<Item>();
		Item item1 = Products.getAtv();
		items.add(item1);
		Item item2 = Products.getIpad();
		items.add(item2);
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		Assert.assertEquals(items, returnedItems);
	}
}
