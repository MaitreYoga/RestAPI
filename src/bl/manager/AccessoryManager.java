package bl.manager;

import java.util.ArrayList;

import dal.factory.Factory;
import dal.product.generic.Accessory;
import dal.product.generic.AccessoryList;

public class AccessoryManager {
	
	private Factory factory;
	
	public AccessoryManager(){
		factory = Factory.getInstance();
	}

	public AccessoryList getAllAccessories() {
		AccessoryList al = factory.makeAccessoryList();
		al.load();
		return al;
	}

	public Accessory addAccessory(String description) {
		Accessory a = factory.makeAccessory();
		a.setDescription(description);
		a.save();
		return a;
	}

	public ArrayList<Accessory> getAccessoriesByRoom(int room) {
		Accessory access = factory.makeAccessory();
		return access.getAccessoriesByRoom(room);
	}

}
