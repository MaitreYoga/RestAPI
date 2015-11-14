package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the period database table.
 * 
 */
@Entity
@NamedQuery(name="Period.findAll", query="SELECT p FROM Period p")
public class Period implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Timestamp enddate;

	private Timestamp startdate;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="period")
	private List<Event> events;

	public Period() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setPeriod(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setPeriod(null);

		return event;
	}

}