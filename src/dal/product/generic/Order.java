package dal.product.generic;

public abstract class Order {
	private int idOrder;
	private String stateOrder;
	private String buyer;
	private String dateOrder;
	
	public abstract int save(int userId, String state, String orderDate);
	public abstract void load(int idOrder2);
	public abstract void updateStateOrder(int idOrder, String state);
	
	public int getIdOrder(){
		return this.idOrder;
	}
	
	public void setIdOrder(int order){
		this.idOrder=order;
	}
	
	public String getStateOrder(){
		return this.stateOrder;
	}
	
	public void setStateOrder(String state){
		this.stateOrder=state;
	}
	
	public String getBuyer() {
		return this.buyer;
	}
	
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
	public String getDateOrder() {
		return dateOrder;
	}
	
	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}

}
