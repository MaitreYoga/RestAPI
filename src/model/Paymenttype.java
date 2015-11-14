package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the paymenttype database table.
 * 
 */
@Entity
@NamedQuery(name="Paymenttype.findAll", query="SELECT p FROM Paymenttype p")
public class Paymenttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String label;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="paymenttype")
	private List<Invoice> invoices;

	//bi-directional many-to-one association to Subscriptionpayment
	@OneToMany(mappedBy="paymenttype")
	private List<Subscriptionpayment> subscriptionpayments;

	public Paymenttype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Invoice addInvoice(Invoice invoice) {
		getInvoices().add(invoice);
		invoice.setPaymenttype(this);

		return invoice;
	}

	public Invoice removeInvoice(Invoice invoice) {
		getInvoices().remove(invoice);
		invoice.setPaymenttype(null);

		return invoice;
	}

	public List<Subscriptionpayment> getSubscriptionpayments() {
		return this.subscriptionpayments;
	}

	public void setSubscriptionpayments(List<Subscriptionpayment> subscriptionpayments) {
		this.subscriptionpayments = subscriptionpayments;
	}

	public Subscriptionpayment addSubscriptionpayment(Subscriptionpayment subscriptionpayment) {
		getSubscriptionpayments().add(subscriptionpayment);
		subscriptionpayment.setPaymenttype(this);

		return subscriptionpayment;
	}

	public Subscriptionpayment removeSubscriptionpayment(Subscriptionpayment subscriptionpayment) {
		getSubscriptionpayments().remove(subscriptionpayment);
		subscriptionpayment.setPaymenttype(null);

		return subscriptionpayment;
	}

}