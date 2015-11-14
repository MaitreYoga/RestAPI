package dao.sql;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.ActivityDAO;
import model.Activity;

@Stateless
public class ActivityDAOSQL implements ActivityDAO{  

	@PersistenceContext(unitName = "ZenLounge")
	private EntityManager em;
	
	public ActivityDAOSQL(){

	}
	
	public void create(Activity Activity) {
		em.getTransaction().begin();
		em.persist(Activity);
		em.getTransaction().commit();
	}

	public List<Activity> findAll(){
		TypedQuery<Activity> query = em.createNamedQuery("Activity.findAll", Activity.class);
		List<Activity> activities = query.getResultList();
	    if (activities.size()>0){
	    	return activities;
	    }
	    else {
	    	return (List<Activity>) new ArrayList<Activity>();
	    }
	}
	

	public Activity findById( int id) {
		return em.find(Activity.class, id);
	}

	public void update(Activity Activity) {
		em.merge(Activity);
	}

	public void delete(int id){
		Activity Activity = em.find(Activity.class, id);
		em.getTransaction().begin();
		em.remove(Activity);
		em.getTransaction().commit();
	}
}
