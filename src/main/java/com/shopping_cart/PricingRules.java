package com.shopping_cart;

import java.util.ArrayList;
import java.util.HashMap;

import com.products.Products;

public class PricingRules {
	private final double IPAD_BULK_PRICE = 499.99;
	
	/**
	 * Applies the pricing rules to the given list of items. 
	 * @param items The given list of items
	 * @return the list of items with their prices adjusted accordingly
	 */
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
			//remove the Apple TVs that should be free
			Item atv = Products.getAtv();
			for (int i = 0; i < numberOfFreeAtvs; i++)
				items.remove(atv);
			//Add the Apple TVs back to the list with adjusted price 
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
		String vgaSku = Products.getVga().getSku();
		Integer mbpCount = itemCounts.getOrDefault(mbpSku, 0);
		Integer vgaCount = itemCounts.getOrDefault(vgaSku, 0);
		if (mbpCount > 0) {
			//remove all VGAs
			items.removeIf(item->item.getSku().equals(vgaSku));
			if (vgaCount < mbpCount)
				vgaCount = mbpCount;
			//Add VGA adapters to the list with adjusted price
			for (int i = 0; i < vgaCount; i++){
				Item item = Products.getVga();
				if (i < mbpCount)
					item.setPrice(0);
				items.add(item);
			}
		}
		
		return items;
	}
}
