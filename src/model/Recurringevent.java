package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recurringevent database table.
 * 
 */
@Entity
@NamedQuery(name="Recurringevent.findAll", query="SELECT r FROM Recurringevent r")
public class Recurringevent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte nbdaysperweek;

	private byte nbmonthsperyear;

	private byte nbweekspermonth;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="idevent")
	private Event event;

	public Recurringevent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getNbdaysperweek() {
		return this.nbdaysperweek;
	}

	public void setNbdaysperweek(byte nbdaysperweek) {
		this.nbdaysperweek = nbdaysperweek;
	}

	public byte getNbmonthsperyear() {
		return this.nbmonthsperyear;
	}

	public void setNbmonthsperyear(byte nbmonthsperyear) {
		this.nbmonthsperyear = nbmonthsperyear;
	}

	public byte getNbweekspermonth() {
		return this.nbweekspermonth;
	}

	public void setNbweekspermonth(byte nbweekspermonth) {
		this.nbweekspermonth = nbweekspermonth;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}