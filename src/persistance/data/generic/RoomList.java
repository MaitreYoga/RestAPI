package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class RoomList {

	private List<Room> rooms;
	
	public RoomList() {
		rooms = new ArrayList<Room>();
	}
	
	public Room get(int index){
		return rooms.get(index);
	}
	public void add(Room r){
		rooms.add(r);
	}
	public int size(){
		return rooms.size();
	}

	public abstract void load();

}
