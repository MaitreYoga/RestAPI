package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the activity database table.
 * 
 */
@Entity
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String longdescription;

	private String name;

	private String shortdescription;

	//bi-directional many-to-one association to Activitycategory
	@ManyToOne
	@JoinColumn(name="idactivitycategory")
	private Activitycategory activitycategory;

	//bi-directional many-to-one association to Manager
	@ManyToOne
	@JoinColumn(name="idmanager")
	private Manager manager;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="activity")
	private List<Event> events;

	public Activity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongdescription() {
		return this.longdescription;
	}

	public void setLongdescription(String longdescription) {
		this.longdescription = longdescription;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortdescription() {
		return this.shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public Activitycategory getActivitycategory() {
		return this.activitycategory;
	}

	public void setActivitycategory(Activitycategory activitycategory) {
		this.activitycategory = activitycategory;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setActivity(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setActivity(null);

		return event;
	}

}