package com.mxxy.game.modler;

import java.util.ArrayList;
import java.util.List;

import com.mxxy.game.resources.Item;
import com.mxxy.game.resources.ItemInstance;
import com.mxxy.game.resources.MedicineItem;

public class MedicineMolder {

	public ItemInstance createItem(String name) {
		return this.createItem(name, 1);
	}
	
	public ItemInstance createItem(String name, int amount) {
		if(name == null || name.isEmpty()) return null;
		name = name.trim();
		Item item = this.findItemByName(name);
		return new ItemInstance(item, amount);
	}
	
	

	private Item findItemByName(String name) {
		List<MedicineItem> results = new ArrayList<MedicineItem>();
		MedicineItem aItem =new MedicineItem();
		aItem.setDescription("z");
		aItem.setId(0001L);
		aItem.setHp(100);
		aItem.setType("type");
		aItem.setName("name");
		results.add(aItem);
		return results.get(0);
	}
}
