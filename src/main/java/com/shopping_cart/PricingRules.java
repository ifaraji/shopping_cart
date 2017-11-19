package com.shopping_cart;

import java.util.ArrayList;
import java.util.HashMap;

import com.products.Products;

public class PricingRules {
	private final double IPAD_BULK_PRICE = 499.99;
	
	public ArrayList<Item> apply(ArrayList<Item> items){
		if (items == null || items.isEmpty())
			throw new RuntimeException("Invalid number of items");
		
		//getting the count of each item
		HashMap<String, Integer> itemCounts = new HashMap<String, Integer>();
		for (Item item : items){
			if (itemCounts.containsKey(item.getSku())) //increment count if already exists
				itemCounts.put(item.getSku(), itemCounts.get(item.getSku())+1);
			else //counting an item type for the first time
				itemCounts.put(item.getSku(), 1);
		}
		
		//Apple TV 3 for 2 deal
		String atvSku = Products.getAtv().getSku();
		Integer atvCount = itemCounts.getOrDefault(atvSku, 0);
		if (atvCount >= 3){
			int numberOfFreeAtvs = atvCount / 3;
			Item atv = Products.getAtv();
			for (int i = 0; i < numberOfFreeAtvs; i++)
				items.remove(atv);
			atv.setPrice(0);
			for (int i = 0; i < numberOfFreeAtvs; i++)
				items.add(atv);
		}
		
		//bulk iPad discount - buy more than 4 and pay 499.99 each
		String ipadSku = Products.getIpad().getSku();
		Integer ipadCount = itemCounts.getOrDefault(ipadSku, 0);
		if (ipadCount > 4) 			
			items.stream()
				.filter(item->item.getSku().equals(ipadSku))
				.forEach(item->item.setPrice(IPAD_BULK_PRICE));
		
		//free VGA adapter with every MacBook Pro
		String mbpSku = Products.getMbp().getSku();
		Integer mbpCount = itemCounts.getOrDefault(mbpSku, 0);
		if (mbpCount > 0)
			for (int i = 0; i < mbpCount; i++){
				Item vga = Products.getVga();
				vga.setPrice(0);
				items.add(vga);
			}
		return items;
	}
}
