package bl.manager;

import java.util.ArrayList;
import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Product;
import dal.product.generic.ProductList;

public class ProductManager {
    private Factory factory;

    public ProductManager(){
    	factory = Factory.getInstance();
    }
    
    public Product getProduct(int idProduct) {
    	Product product = factory.makeProduct();
    	product.load(idProduct);
    	
    	if(product.isNull()){
    		return null;
    	}
    	else{
    		return product;
    	}
    }
    

    public List<Product> getProducts() {
    	ProductList productL = factory.makeProductList();
    	productL.load();
    	return productL.getList();
    }
    
	public List<Integer> getSellers(List<Integer> idProducts) {
		Product product = factory.makeProduct();
		List<Integer> sellers = new ArrayList<Integer>();
		for (int i = 0; i<idProducts.size(); i++){
			product.load(idProducts.get(i));
			sellers.add(product.getSeller());
		}
	
		return sellers;
	}
	
	public int getSeller(int idProduct) {
		Product product = factory.makeProduct();
		product.load(idProduct);
		return product.getSeller();
	}
	public String save(int id, int productCat,String name, String brand, int price, int memberPrice,
			int quantity, String url) {
		Product product = factory.makeProduct();
		product.setName(name);
		product.setBrand(brand);
		product.setPrice(price);
		product.setMemberPrice(memberPrice);
		product.setQuantityAvailable(quantity);
		product.setUrlImage(url);
		product.setSeller(id);
		product.setProductCat(productCat);
		return product.save();
	}
	public ProductList getProductsFromMember(int idMember) {
		ProductList productL = factory.makeProductList();
		productL.load(idMember);
		return productL;
	}
	public void updateQuantityAvailable(int idProd, int newQuant) {
		Product product = factory.makeProduct();
		product.updateQuantityAvailable(idProd, newQuant);	
	}
}
