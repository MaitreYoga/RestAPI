package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Timestamp joiningdate;

	//bi-directional many-to-one association to Inscription
	@OneToMany(mappedBy="member")
	private List<Inscription> inscriptions;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="member")
	private List<Product> products;

	//bi-directional many-to-one association to Subscriptionpayment
	@OneToMany(mappedBy="member")
	private List<Subscriptionpayment> subscriptionpayments;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="member")
	private List<User> users;

	public Member() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getJoiningdate() {
		return this.joiningdate;
	}

	public void setJoiningdate(Timestamp joiningdate) {
		this.joiningdate = joiningdate;
	}

	public List<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Inscription addInscription(Inscription inscription) {
		getInscriptions().add(inscription);
		inscription.setMember(this);

		return inscription;
	}

	public Inscription removeInscription(Inscription inscription) {
		getInscriptions().remove(inscription);
		inscription.setMember(null);

		return inscription;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setMember(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setMember(null);

		return product;
	}

	public List<Subscriptionpayment> getSubscriptionpayments() {
		return this.subscriptionpayments;
	}

	public void setSubscriptionpayments(List<Subscriptionpayment> subscriptionpayments) {
		this.subscriptionpayments = subscriptionpayments;
	}

	public Subscriptionpayment addSubscriptionpayment(Subscriptionpayment subscriptionpayment) {
		getSubscriptionpayments().add(subscriptionpayment);
		subscriptionpayment.setMember(this);

		return subscriptionpayment;
	}

	public Subscriptionpayment removeSubscriptionpayment(Subscriptionpayment subscriptionpayment) {
		getSubscriptionpayments().remove(subscriptionpayment);
		subscriptionpayment.setMember(null);

		return subscriptionpayment;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setMember(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setMember(null);

		return user;
	}

}