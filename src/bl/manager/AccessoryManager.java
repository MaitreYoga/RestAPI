package bl.manager;

import java.util.ArrayList;

import persistance.data.generic.Accessory;
import persistance.data.generic.AccessoryList;
import persistance.factory.Factory;

public class AccessoryManager {
	
	private Factory factory;
	private AccessoryList accessories;
	
	public AccessoryManager(){
		factory = Factory.getInstance();
	}

	public AccessoryList getAllAccessories() {
		accessories = factory.makeAccessoryList();
		accessories.load();
		return accessories;
	}

	public Accessory addAccessory(String description) {
		Accessory access = factory.makeAccessory();
		access.setDescription(description);
		accessories.add(access);
		access.save();
		return access;
	}

	public ArrayList<Accessory> getAccessoriesByRoom(int room) {
		Accessory access = factory.makeAccessory();
		return access.getAccessoriesByRoom(room);
	}

}
