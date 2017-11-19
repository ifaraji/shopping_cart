package com.shopping_cart;

import org.junit.Before;
import org.junit.Test;

import com.products.Products;

import org.junit.Assert;

public class CheckoutTest {
	PricingRules pricingRules;
	Checkout co;
	
	@Before
	public void init(){
		pricingRules = new PricingRules();
		co = new Checkout(pricingRules);
	}
	
	@Test
	public void whenItemIsScanned(){
		Item item = Products.getAtv();
		co.scan(item);
		Assert.assertEquals(1, co.getItems().size());
	}
	
	@Test
	public void whenMultipleItemsAreScanned(){ 
		co.scan(Products.getAtv());
		co.scan(Products.getMbp());
		co.scan(Products.getVga());
		co.scan(Products.getIpad());
		Assert.assertEquals(4, co.getItems().size());
	}
}
