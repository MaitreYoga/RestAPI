package bl.manager;

import dal.Session;
import dal.factory.Factory;
import dal.product.generic.Product;
import dal.product.generic.User;

public class SaleManager {
	private Factory factory;
	private User user;
	private Product product;
	
	public SaleManager(){
		this.factory = Factory.getInstance();
		this.user = Session.user();
	}
	
	public String sellProduct(){
		this.product = factory.makeProduct();
		return null;
	}
}
