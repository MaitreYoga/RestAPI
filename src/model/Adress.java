package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adress database table.
 * 
 */
@Entity
@NamedQuery(name="Adress.findAll", query="SELECT a FROM Adress a")
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private int number;

	private int postalcode;

	private String town;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="adress")
	private List<User> users;

	public Adress() {
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

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setAdress(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAdress(null);

		return user;
	}

}