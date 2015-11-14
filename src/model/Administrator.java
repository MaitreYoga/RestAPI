package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the administrator database table.
 * 
 */
@Entity
@NamedQuery(name="Administrator.findAll", query="SELECT a FROM Administrator a")
public class Administrator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="administrator")
	private List<User> users;

	public Administrator() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setAdministrator(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAdministrator(null);

		return user;
	}

}