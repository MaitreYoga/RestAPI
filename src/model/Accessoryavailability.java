package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accessoryavailability database table.
 * 
 */
@Entity
@NamedQuery(name="Accessoryavailability.findAll", query="SELECT a FROM Accessoryavailability a")
public class Accessoryavailability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int quantity;

	//bi-directional many-to-one association to Accessory
	@ManyToOne
	@JoinColumn(name="idaccessory")
	private Accessory accessory;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="idroom")
	private Room room;

	public Accessoryavailability() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Accessory getAccessory() {
		return this.accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}