package persistance.data.generic;

import java.util.ArrayList;

public abstract class Accessory {

	private String description;
	private String id;
	
	public Accessory(){
		description = "";
		setID("");
	}
	
	public Accessory(String description, int id){
		this.description = description;
		this.id = String.valueOf(id);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String value){
		description = value;
	}
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
	public String toString(){
		return this.getDescription();
	}
	
	public abstract ArrayList<Accessory> getAccessoriesByRoom(int idRoom);

	public abstract void save();

	public abstract void update();

	public abstract void delete();

}
