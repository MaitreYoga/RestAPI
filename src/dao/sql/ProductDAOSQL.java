package dao.sql;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.interfaceDAO.ProductDAO;
import model.Product;

@Stateless
public class ProductDAOSQL implements ProductDAO{  
	
	@PersistenceContext(unitName = "ZenLounge")
	private EntityManager em;
	
	public ProductDAOSQL(){

	}
	
	public void create(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}

	public List<Product> findAll(){
		TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
		List<Product> products = query.getResultList();
	    if (products.size()>0){
	    	return products;
	    }
	    else {
	    	return (List<Product>) new ArrayList<Product>();
	    }
	}
	

	public Product findById( int id) {
		return em.find(Product.class, id);
	}

	public void update(Product product) {
		em.merge(product);
	}

	public void delete(int id){
		Product product = em.find(Product.class, id);
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
	}
}
