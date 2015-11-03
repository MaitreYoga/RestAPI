package bl.manager;

import java.util.ArrayList;
import java.util.List;

import persistance.factory.Factory;
import persistance.data.generic.Product;
import persistance.data.generic.ProductList;

public class ProductManager {
    private Factory factory;
    private Product product;
    private ProductList productL;

    public ProductManager(){
    	factory = Factory.getInstance();
    }
    public Product getProduct(int idProduct) {
    	this.product = factory.makeProduct();
    	this.product.load(idProduct);
    	return this.product;
    }
    
	public List<Integer> getSellers(List<Integer> idProducts) {
		this.product = factory.makeProduct();
		List<Integer> sellers = new ArrayList<Integer>();
		for (int i = 0; i<idProducts.size(); i++){
			this.product.load(idProducts.get(i));
			sellers.add(this.product.getSeller());
		}
	
		return sellers;
	}
	
	public int getSeller(int idProduct) {
		this.product = factory.makeProduct();
		this.product.load(idProduct);
		return this.product.getSeller();
	}
	public String save(int id, int productCat,String name, String brand, int price, int memberPrice,
			int quantity, String url) {
		this.product = factory.makeProduct();
		this.product.setName(name);
		this.product.setBrand(brand);
		this.product.setPrice(price);
		this.product.setMemberPrice(memberPrice);
		this.product.setQuantityAvailable(quantity);
		this.product.setUrlImage(url);
		this.product.setSeller(id);
		this.product.setProductCat(productCat);
		return this.product.save();
	}
	public ProductList getProductsFromMember(int idMember) {
		productL = factory.makeProductList();
		productL.load(idMember);
		return productL;
	}
	public void updateQuantityAvailable(int idProd, int newQuant) {
		product = factory.makeProduct();
		product.updateQuantityAvailable(idProd, newQuant);
		
	}

}
