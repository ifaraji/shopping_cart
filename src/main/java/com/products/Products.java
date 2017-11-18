package com.products;

import com.shopping_cart.Item;

public class Products {
	public static Item getIpad(){
		Item item = new Item();
		item.setSku("ipad");
		item.setName("Super iPad");
		item.setPrice(549.99);
		return item;
	}
	
	public static Item getMbp(){
		Item item = new Item();
		item.setSku("mbp");
		item.setName("MacBook Pro");
		item.setPrice(1399.99);
		return item;
	}
	
	public static Item getAtv(){
		Item item = new Item();
		item.setSku("atv");
		item.setName("Apple TV");
		item.setPrice(109.50);
		return item;
	}
	
	public static Item getVga(){
		Item item = new Item();
		item.setSku("vga");
		item.setName("VGA adapter");
		item.setPrice(30.00);
		return item;
	}

}
