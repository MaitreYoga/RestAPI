package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private int price;

	//bi-directional many-to-one association to Activity
	@ManyToOne
	@JoinColumn(name="idactivity")
	private Activity activity;

	//bi-directional many-to-one association to Period
	@ManyToOne
	@JoinColumn(name="idperiod")
	private Period period;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="idroom")
	private Room room;

	//bi-directional many-to-one association to Speaker
	@ManyToOne
	@JoinColumn(name="idspeaker")
	private Speaker speaker;

	//bi-directional many-to-one association to Inscription
	@OneToMany(mappedBy="event")
	private List<Inscription> inscriptions;

	//bi-directional many-to-one association to Recurringevent
	@OneToMany(mappedBy="event")
	private List<Recurringevent> recurringevents;

	public Event() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Speaker getSpeaker() {
		return this.speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public List<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Inscription addInscription(Inscription inscription) {
		getInscriptions().add(inscription);
		inscription.setEvent(this);

		return inscription;
	}

	public Inscription removeInscription(Inscription inscription) {
		getInscriptions().remove(inscription);
		inscription.setEvent(null);

		return inscription;
	}

	public List<Recurringevent> getRecurringevents() {
		return this.recurringevents;
	}

	public void setRecurringevents(List<Recurringevent> recurringevents) {
		this.recurringevents = recurringevents;
	}

	public Recurringevent addRecurringevent(Recurringevent recurringevent) {
		getRecurringevents().add(recurringevent);
		recurringevent.setEvent(this);

		return recurringevent;
	}

	public Recurringevent removeRecurringevent(Recurringevent recurringevent) {
		getRecurringevents().remove(recurringevent);
		recurringevent.setEvent(null);

		return recurringevent;
	}

}