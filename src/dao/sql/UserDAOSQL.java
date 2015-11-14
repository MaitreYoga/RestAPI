package dao.sql;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.interfaceDAO.UserDAO;
import model.User;

@Stateless
public class UserDAOSQL implements UserDAO{  
	
	@PersistenceContext(unitName = "ZenLounge")
	private EntityManager em;
	
	public UserDAOSQL(){

	}
	
	public void create(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public List<User> findAll(){
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		List<User> users = query.getResultList();
	    if (users.size()>0){
	    	return users;
	    }
	    else {
	    	return (List<User>) new ArrayList<User>();
	    }
	}
	

	public User findById( int id) {
		return em.find(User.class, id);
	}

	public void update(User user) {
		em.merge(user);
	}

	public void delete(int id){
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
