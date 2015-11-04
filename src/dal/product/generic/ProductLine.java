package dal.product.generic;

import java.util.List;


public abstract class ProductLine {

	private int idProduct;
	private String prodName;
	private int quantity;
	private int unitPrice;
	private int memberPrice;
	
	public int getIdProduct(){
		return this.idProduct;
	}
	
	public String getProdName() {
		return this.prodName;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(int priceProd) {
		this.unitPrice = priceProd;
	}

	public int getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(int memberPrice) {
		this.memberPrice = memberPrice;
	}
}
