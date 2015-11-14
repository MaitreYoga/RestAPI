package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trainingroom database table.
 * 
 */
@Entity
@NamedQuery(name="Trainingroom.findAll", query="SELECT t FROM Trainingroom t")
public class Trainingroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int capacity;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="idroom")
	private Room room;

	public Trainingroom() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}