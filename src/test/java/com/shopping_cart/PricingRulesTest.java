package com.shopping_cart;

import java.util.ArrayList;

import org.junit.Test;

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
	
}
