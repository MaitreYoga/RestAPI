package dao.sql;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.interfaceDAO.AccessoryDAO;
import model.Accessory;

@Stateless
public class AccessoryDAOSQL implements AccessoryDAO{  
	
	@PersistenceContext(unitName = "ZenLounge")
	private EntityManager em;
	
	public AccessoryDAOSQL(){

	}
	
	public void create(Accessory accessory) {
		em.getTransaction().begin();
		em.persist(accessory);
		em.getTransaction().commit();
	}

	public List<Accessory> findAll(){
		TypedQuery<Accessory> query = em.createNamedQuery("Accessory.findAll", Accessory.class);
		List<Accessory> accessories = query.getResultList();
	    if (accessories.size()>0){
	    	return accessories;
	    }
	    else {
	    	return (List<Accessory>) new ArrayList<Accessory>();
	    }
	}
	

	public Accessory findById( int id) {
		return em.find(Accessory.class, id);
	}

	public void update(Accessory accessory) {
		em.merge(accessory);
	}

	public void delete(int id){
		Accessory accessory = em.find(Accessory.class, id);
		em.getTransaction().begin();
		em.remove(accessory);
		em.getTransaction().commit();
	}
}
