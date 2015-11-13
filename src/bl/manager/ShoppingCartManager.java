package bl.manager;

import dal.factory.Factory;
import dal.product.generic.ProductLineList;
import dal.product.generic.ShoppingCart;
import dal.product.generic.ShoppingCartLine;

public class ShoppingCartManager {
    public Factory factory;

	public ShoppingCartManager(){
		factory = Factory.getInstance();
	}
    
    public ProductLineList getAllProductInCart(int userId) {
    	ProductLineList prods = factory.makeProductLineList();
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
		factory.makeProductLineList().updateProd(product, quantity, userId);
	}

	public void addToCart(int idProd, int userId) {
		ShoppingCartLine prod = factory.makeShoppingCartLine();
		ShoppingCart cart = factory.makeShoppingCart();
		int idCart = cart.getIdFromOwner(userId);
		prod.save(idProd, idCart);
	}

	public void createShoppingCart(int userId) {
		factory.makeShoppingCart().save(userId);
	}
}