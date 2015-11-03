package bl.manager;

import persistance.factory.Factory;
import persistance.data.generic.ProductLineList;
import persistance.data.generic.ShoppingCart;
import persistance.data.generic.ShoppingCartLine;

public class ShoppingCartManager {
	private ProductLineList prods;
	private ShoppingCart cart;
    public Factory factory;

	public ShoppingCartManager(){
		factory = Factory.getInstance();
	}
    
    public ProductLineList getAllProductInCart(int userId) {
    	prods = factory.makeProductLineList();
    	prods.load(userId);
    	return prods;
    }

    public void handleRemove(int product, int userId) {
    	ShoppingCartLine prod = factory.makeShoppingCartLine();
    	ShoppingCart cart = factory.makeShoppingCart();
    	int idShoppingCart = cart.getIdFromOwner(userId);
    	prod.delete(product, idShoppingCart);
    }

	public void handleChangeQuantity(int product, int quantity, int userId) {
		prods.updateProd(product, quantity, userId);
	}

	public void addToCart(int idProd, int userId) {
		ShoppingCartLine prod = factory.makeShoppingCartLine();
		ShoppingCart cart = factory.makeShoppingCart();
		int idCart = cart.getIdFromOwner(userId);
		prod.save(idProd, idCart);
	}

	public void createShoppingCart(int userId) {
		cart = factory.makeShoppingCart();
		cart.save(userId);
		
	}

}
