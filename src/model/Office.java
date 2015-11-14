package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the office database table.
 * 
 */
@Entity
@NamedQuery(name="Office.findAll", query="SELECT o FROM Office o")
public class Office implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="idroom")
	private Room room;

	public Office() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}