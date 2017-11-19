package com.products;

import com.shopping_cart.Item;

public class Products {
	public static Item getIpad(){
		Item item = new Item("Super iPad", "ipad", 549.99);
		return item;
	}
	
	public static Item getMbp(){
		Item item = new Item("MacBook Pro", "mbp", 1399.99);
		return item;
	}
	
	public static Item getAtv(){
		Item item = new Item("Apple TV", "atv", 109.50);
		return item;
	}
	
	public static Item getVga(){
		Item item = new Item("VGA adapter", "vga", 30.00);
		return item;
	}

}
