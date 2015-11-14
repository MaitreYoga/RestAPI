package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte isvalidated;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="idinvoice")
	private Invoice invoice;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User user;

	//bi-directional many-to-one association to Productline
	@OneToMany(mappedBy="order")
	private List<Productline> productlines;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsvalidated() {
		return this.isvalidated;
	}

	public void setIsvalidated(byte isvalidated) {
		this.isvalidated = isvalidated;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Productline> getProductlines() {
		return this.productlines;
	}

	public void setProductlines(List<Productline> productlines) {
		this.productlines = productlines;
	}

	public Productline addProductline(Productline productline) {
		getProductlines().add(productline);
		productline.setOrder(this);

		return productline;
	}

	public Productline removeProductline(Productline productline) {
		getProductlines().remove(productline);
		productline.setOrder(null);

		return productline;
	}

}