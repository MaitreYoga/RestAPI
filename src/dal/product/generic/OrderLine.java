package dal.product.generic;

import java.util.List;


public abstract class OrderLine {
	
	private int idOrder;
	private int idProduct;
	private int quantity;
	
	public int getIdProduct(){
		return this.idProduct;
	}
	
	public int getIdOrder() {
		return this.idOrder;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setIdOrder(int idOrder){
		this.idOrder = idOrder;
	}
	
	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public abstract void save(List<ProductLine> productsS, int idOrder);

}
