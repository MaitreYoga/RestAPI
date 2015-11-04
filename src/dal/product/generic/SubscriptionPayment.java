package dal.product.generic;

public abstract class SubscriptionPayment {
	private int idSubscriptionPayment;
	private int  amountPaid;
	private String datePayment;
	private String paymentType;
	private int idMember;
	
	public abstract int save(int amountPaid, String paymentDate, String paymentType, int idMember);
	public abstract void load(int idSubscriptionPayment);
	public abstract SubscriptionPayment getLastSubscriptionPayment(int idMember);
	
	public int getIdSubscriptionPayment() {
		return idSubscriptionPayment;
	}
	
	public void setIdSubscriptionPayment(int idSubscriptionPayment) {
		this.idSubscriptionPayment = idSubscriptionPayment;
	}
	
	public String getDatePayment() {
		return datePayment;
	}
	
	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}
	
	public int getAmountPaid() {
		return amountPaid;
	}
	
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public int getIdMember() {
		return idMember;
	}
	
	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


}
