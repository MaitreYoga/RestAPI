package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productline database table.
 * 
 */
@Entity
@NamedQuery(name="Productline.findAll", query="SELECT p FROM Productline p")
public class Productline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idorder")
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="idproduct")
	private Product product;

	//bi-directional many-to-one association to Shoppingcart
	@ManyToOne
	@JoinColumn(name="idshoppingcart")
	private Shoppingcart shoppingcart;

	public Productline() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shoppingcart getShoppingcart() {
		return this.shoppingcart;
	}

	public void setShoppingcart(Shoppingcart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}

}