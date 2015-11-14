package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String connectiontoken;

	private String firstname;

	private String lastname;

	private String login;

	private String mailadress;

	private String password;

	private String phonenumber;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="user1")
	private List<Notification> notifications1;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="user2")
	private List<Notification> notifications2;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user")
	private List<Order> orders;

	//bi-directional many-to-one association to Shoppingcart
	@OneToMany(mappedBy="user")
	private List<Shoppingcart> shoppingcarts;

	//bi-directional many-to-one association to Administrator
	@ManyToOne
	@JoinColumn(name="idadministrator")
	private Administrator administrator;

	//bi-directional many-to-one association to Adress
	@ManyToOne
	@JoinColumn(name="idadress")
	private Adress adress;

	//bi-directional many-to-one association to Manager
	@ManyToOne
	@JoinColumn(name="idmanager")
	private Manager manager;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="idmember")
	private Member member;

	//bi-directional many-to-one association to Speaker
	@ManyToOne
	@JoinColumn(name="idspeaker")
	private Speaker speaker;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConnectiontoken() {
		return this.connectiontoken;
	}

	public void setConnectiontoken(String connectiontoken) {
		this.connectiontoken = connectiontoken;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMailadress() {
		return this.mailadress;
	}

	public void setMailadress(String mailadress) {
		this.mailadress = mailadress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Notification> getNotifications1() {
		return this.notifications1;
	}

	public void setNotifications1(List<Notification> notifications1) {
		this.notifications1 = notifications1;
	}

	public Notification addNotifications1(Notification notifications1) {
		getNotifications1().add(notifications1);
		notifications1.setUser1(this);

		return notifications1;
	}

	public Notification removeNotifications1(Notification notifications1) {
		getNotifications1().remove(notifications1);
		notifications1.setUser1(null);

		return notifications1;
	}

	public List<Notification> getNotifications2() {
		return this.notifications2;
	}

	public void setNotifications2(List<Notification> notifications2) {
		this.notifications2 = notifications2;
	}

	public Notification addNotifications2(Notification notifications2) {
		getNotifications2().add(notifications2);
		notifications2.setUser2(this);

		return notifications2;
	}

	public Notification removeNotifications2(Notification notifications2) {
		getNotifications2().remove(notifications2);
		notifications2.setUser2(null);

		return notifications2;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public List<Shoppingcart> getShoppingcarts() {
		return this.shoppingcarts;
	}

	public void setShoppingcarts(List<Shoppingcart> shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

	public Shoppingcart addShoppingcart(Shoppingcart shoppingcart) {
		getShoppingcarts().add(shoppingcart);
		shoppingcart.setUser(this);

		return shoppingcart;
	}

	public Shoppingcart removeShoppingcart(Shoppingcart shoppingcart) {
		getShoppingcarts().remove(shoppingcart);
		shoppingcart.setUser(null);

		return shoppingcart;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Speaker getSpeaker() {
		return this.speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

}