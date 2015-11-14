package dao.interfaceDAO;

import java.util.List;
import model.Product;

public interface ProductDAO {

	public void create(Product product);

	public List<Product> findAll();
	
	public Product findById( int id);

	public void update(Product product);

	public void delete(int id);
}

