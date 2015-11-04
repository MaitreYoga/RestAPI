package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductList {

	private List<Product> products;
	
	public ProductList(){
		products = new ArrayList<Product>();
	}
	
	public int size(){
		return products.size();
	}
	
	public void add(Product product){
		products.add(product);
	}
	
	public void remove(int index){
		products.remove(index);
	}
	
	public Product get(int index){
		return products.get(index);
	}
	
	public abstract void load(int idMember);
}
