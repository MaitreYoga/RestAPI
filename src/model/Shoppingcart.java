package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shoppingcart database table.
 * 
 */
@Entity
@NamedQuery(name="Shoppingcart.findAll", query="SELECT s FROM Shoppingcart s")
public class Shoppingcart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Productline
	@OneToMany(mappedBy="shoppingcart")
	private List<Productline> productlines;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User user;

	public Shoppingcart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Productline> getProductlines() {
		return this.productlines;
	}

	public void setProductlines(List<Productline> productlines) {
		this.productlines = productlines;
	}

	public Productline addProductline(Productline productline) {
		getProductlines().add(productline);
		productline.setShoppingcart(this);

		return productline;
	}

	public Productline removeProductline(Productline productline) {
		getProductlines().remove(productline);
		productline.setShoppingcart(null);

		return productline;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}