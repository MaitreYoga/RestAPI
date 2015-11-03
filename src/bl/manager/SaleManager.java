package bl.manager;

import persistance.Session;
import persistance.data.generic.Product;
import persistance.data.generic.User;
import persistance.factory.Factory;

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
