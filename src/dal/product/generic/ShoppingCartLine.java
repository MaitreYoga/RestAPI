package dal.product.generic;

public abstract class ShoppingCartLine {

	private int idShoppingCart;
	private int idProduct;
	private int quantity;
	
	public int getIdProduct(){
		return this.idProduct;
	}
	
	public int getIdShoppingCart() {
		return idShoppingCart;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setIdShoppingCart(int idShoppingCart) {
		this.idShoppingCart = idShoppingCart;
	}

	public abstract void delete(int product, int idShoppingCart);
	public abstract void save(int product, int idShoppingCart);
	
}
