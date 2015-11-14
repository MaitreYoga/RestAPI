package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the manager database table.
 * 
 */
@Entity
@NamedQuery(name="Manager.findAll", query="SELECT m FROM Manager m")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Activity
	@OneToMany(mappedBy="manager")
	private List<Activity> activities;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="manager")
	private List<User> users;

	public Manager() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Activity addActivity(Activity activity) {
		getActivities().add(activity);
		activity.setManager(this);

		return activity;
	}

	public Activity removeActivity(Activity activity) {
		getActivities().remove(activity);
		activity.setManager(null);

		return activity;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setManager(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setManager(null);

		return user;
	}

}