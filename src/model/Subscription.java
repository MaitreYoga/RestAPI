package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the subscription database table.
 * 
 */
@Entity
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float anualfee;

	private Timestamp date;

	public Subscription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAnualfee() {
		return this.anualfee;
	}

	public void setAnualfee(float anualfee) {
		this.anualfee = anualfee;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}