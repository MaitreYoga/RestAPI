package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the subscriptionpayment database table.
 * 
 */
@Entity
@NamedQuery(name="Subscriptionpayment.findAll", query="SELECT s FROM Subscriptionpayment s")
public class Subscriptionpayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float amountpaid;

	private Timestamp paymentdate;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="idmember")
	private Member member;

	//bi-directional many-to-one association to Paymenttype
	@ManyToOne
	@JoinColumn(name="idpaymenttype")
	private Paymenttype paymenttype;

	public Subscriptionpayment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmountpaid() {
		return this.amountpaid;
	}

	public void setAmountpaid(float amountpaid) {
		this.amountpaid = amountpaid;
	}

	public Timestamp getPaymentdate() {
		return this.paymentdate;
	}

	public void setPaymentdate(Timestamp paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Paymenttype getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}

}