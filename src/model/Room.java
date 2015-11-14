package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private int surface;

	//bi-directional many-to-one association to Accessoryavailability
	@OneToMany(mappedBy="room")
	private List<Accessoryavailability> accessoryavailabilities;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="room")
	private List<Event> events;

	//bi-directional many-to-one association to Office
	@OneToMany(mappedBy="room")
	private List<Office> offices;

	//bi-directional many-to-one association to Trainingroom
	@OneToMany(mappedBy="room")
	private List<Trainingroom> trainingrooms;

	public Room() {
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

	public int getSurface() {
		return this.surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public List<Accessoryavailability> getAccessoryavailabilities() {
		return this.accessoryavailabilities;
	}

	public void setAccessoryavailabilities(List<Accessoryavailability> accessoryavailabilities) {
		this.accessoryavailabilities = accessoryavailabilities;
	}

	public Accessoryavailability addAccessoryavailability(Accessoryavailability accessoryavailability) {
		getAccessoryavailabilities().add(accessoryavailability);
		accessoryavailability.setRoom(this);

		return accessoryavailability;
	}

	public Accessoryavailability removeAccessoryavailability(Accessoryavailability accessoryavailability) {
		getAccessoryavailabilities().remove(accessoryavailability);
		accessoryavailability.setRoom(null);

		return accessoryavailability;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setRoom(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setRoom(null);

		return event;
	}

	public List<Office> getOffices() {
		return this.offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public Office addOffice(Office office) {
		getOffices().add(office);
		office.setRoom(this);

		return office;
	}

	public Office removeOffice(Office office) {
		getOffices().remove(office);
		office.setRoom(null);

		return office;
	}

	public List<Trainingroom> getTrainingrooms() {
		return this.trainingrooms;
	}

	public void setTrainingrooms(List<Trainingroom> trainingrooms) {
		this.trainingrooms = trainingrooms;
	}

	public Trainingroom addTrainingroom(Trainingroom trainingroom) {
		getTrainingrooms().add(trainingroom);
		trainingroom.setRoom(this);

		return trainingroom;
	}

	public Trainingroom removeTrainingroom(Trainingroom trainingroom) {
		getTrainingrooms().remove(trainingroom);
		trainingroom.setRoom(null);

		return trainingroom;
	}

}