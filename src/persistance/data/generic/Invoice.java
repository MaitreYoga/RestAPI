package persistance.data.generic;

public abstract class Invoice {
	private int idInvoice;
	private int  amountPaid;
	private String paymentType;
	private String paymentDate;
	private int idOrder;
	
	public abstract int save(int amountPaid, String paymentDate, String paymentType, int idOrder);
	public abstract void load(int idInvoice);
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

}
