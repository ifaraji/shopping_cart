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
	
	@Test
	public void whenThreeAppleTVsOnlyPayForTwo(){
		ArrayList<Item> items = new ArrayList<Item>();
		double appleTvPrice = Products.getAtv().getPrice();
		items.add(Products.getAtv());
		items.add(Products.getAtv());
		items.add(Products.getAtv());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		double finalPrice = 0;
		for (Item item : returnedItems)
			finalPrice += item.getPrice();
		Assert.assertTrue(2*appleTvPrice == finalPrice);
	}	
	
	@Test
	public void whenThreeAppleTVsOnlyPayForTwoPlusOtherProduct(){
		ArrayList<Item> items = new ArrayList<Item>();
		double appleTvPrice = Products.getAtv().getPrice();
		double iPadPrice = Products.getIpad().getPrice();
		items.add(Products.getAtv());
		items.add(Products.getAtv());
		items.add(Products.getAtv());
		items.add(Products.getIpad());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		double finalPrice = 0;
		for (Item item : returnedItems)
			finalPrice += item.getPrice();
		Assert.assertTrue(2*appleTvPrice + iPadPrice == finalPrice);
	}
	
	@Test
	public void whenMoreThanFourIpadPayLess(){
		ArrayList<Item> items = new ArrayList<Item>();
		//double iPadPrice = Products.getIpad().getPrice();
		double iPadBulkPrice = 499.99;
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		double finalPrice = 0;
		for (Item item : returnedItems)
			finalPrice += item.getPrice();
		Assert.assertTrue(items.size()*iPadBulkPrice == finalPrice);
	}
	
	@Test
	public void whenLessThanFourIpadPayNormal(){
		ArrayList<Item> items = new ArrayList<Item>();
		double iPadPrice = Products.getIpad().getPrice();
		//double iPadBulkPrice = 499.99;
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		double finalPrice = 0;
		for (Item item : returnedItems)
			finalPrice += item.getPrice();
		Assert.assertTrue(items.size()*iPadPrice == finalPrice);
	}
	
	@Test
	public void whenFourIpadPayNormal(){
		ArrayList<Item> items = new ArrayList<Item>();
		double iPadPrice = Products.getIpad().getPrice();
		//double iPadBulkPrice = 499.99;
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		items.add(Products.getIpad());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		double finalPrice = 0;
		for (Item item : returnedItems)
			finalPrice += item.getPrice();
		Assert.assertTrue(items.size()*iPadPrice == finalPrice);
	}
	
	@Test
	public void whenOneMacBookProGetOneFreeVgaAdapter(){
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(Products.getMbp());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		Assert.assertTrue(returnedItems.size() == 2);
		Assert.assertTrue(returnedItems.contains(Products.getVga()));
	}
	
	@Test
	public void whenAnyMacBookProGetFreeVgaAdapters(){
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(Products.getMbp());
		items.add(Products.getMbp());
		items.add(Products.getMbp());
		PricingRules pricingRules = new PricingRules();
		ArrayList<Item> returnedItems = pricingRules.apply(items);
		String mbpSku = Products.getMbp().getSku();
		String atvSku = Products.getAtv().getSku();
		long mbpCount = returnedItems.stream().filter(item->item.getSku().equals(mbpSku)).count();
		long atvCount = returnedItems.stream().filter(item->item.getSku().equals(atvSku)).count();
		Assert.assertTrue(mbpCount == atvCount);
	}
}
