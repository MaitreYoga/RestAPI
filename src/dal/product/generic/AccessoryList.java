package dal.product.generic;

import java.util.ArrayList;
import java.util.List;


public abstract class AccessoryList {

	private List<Accessory> accessories;
	
	public AccessoryList(){
		accessories = new ArrayList<Accessory>();
	}
	
	public int size() {
		return accessories.size();
	}

	public Accessory get(int i) {
		return accessories.get(i);
	}
	
	public void add(Accessory access){
		accessories.add(access);
	}
	public void remove(Accessory access){
		accessories.remove(access);
	}
	public void remove(int index){
		accessories.remove(index);
	}
	
	public List<Accessory> getList(){
		return this.accessories;
	}
	public abstract void load();
}
