package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the speaker database table.
 * 
 */
@Entity
@NamedQuery(name="Speaker.findAll", query="SELECT s FROM Speaker s")
public class Speaker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String detaileddescription;

	private String job;

	private String shortdescription;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="speaker")
	private List<Event> events;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="speaker")
	private List<User> users;

	public Speaker() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetaileddescription() {
		return this.detaileddescription;
	}

	public void setDetaileddescription(String detaileddescription) {
		this.detaileddescription = detaileddescription;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getShortdescription() {
		return this.shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setSpeaker(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setSpeaker(null);

		return event;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setSpeaker(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setSpeaker(null);

		return user;
	}

}