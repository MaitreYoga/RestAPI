package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int amountpaid;

	private Timestamp paymentdate;

	//bi-directional many-to-one association to Paymenttype
	@ManyToOne
	@JoinColumn(name="idpaymenttype")
	private Paymenttype paymenttype;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="invoice")
	private List<Order> orders;

	public Invoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountpaid() {
		return this.amountpaid;
	}

	public void setAmountpaid(int amountpaid) {
		this.amountpaid = amountpaid;
	}

	public Timestamp getPaymentdate() {
		return this.paymentdate;
	}

	public void setPaymentdate(Timestamp paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Paymenttype getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setInvoice(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setInvoice(null);

		return order;
	}

}