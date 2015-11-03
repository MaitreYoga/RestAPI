package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;


public abstract class ProductLineList {
	
	private List<ProductLine> prods;
	
	public ProductLineList(){
		prods = new ArrayList<ProductLine>();
	}

	public abstract void load(int userId);
	public abstract void deleteProd(int product, int userId);
	public abstract void updateProd(int product, int quantity, int userId);
	
	public void add(ProductLine prod){
		prods.add(prod);
	}

	public int size() {
		return prods.size();
	}

	public ProductLine get(int index) {
		return prods.get(index);
	}
	
	public void remove(int index){
		prods.remove(index);
	}
}
