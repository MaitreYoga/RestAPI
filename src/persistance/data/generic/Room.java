package persistance.data.generic;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import persistance.data.mysql.MSRoom;

public abstract class Room {

	private int idRoom;
	private String name;
	private int surface;
	private int capacity;
	
	public Room(int idRoom, String name, int surface, int capacity) {
		this.idRoom=idRoom;
		this.name = name;
		this.surface = surface;
		this.capacity = capacity;
	}
	
	public Room() {
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

	public abstract String load(String placeID);

	public abstract List<Room> load();

	public abstract boolean deleteRoom(String roomName);

	public abstract boolean createRoom(String room, int surface);

	public abstract String loadList();

	public abstract int deleteAccessoryFromRoom(int idRoom, int idAccessory);

	public abstract int addAccessoryToRoom(int idAccessory, int idRoom2);

}
