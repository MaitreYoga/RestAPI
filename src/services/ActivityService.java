package services;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.interfaceDAO.ActivityDAO;
import model.Activity;

@Path("activities")
@Produces("application/json")
@Consumes("application/json")
public class ActivityService {

	@EJB
	ActivityDAO activitydao;
	
	public ActivityService(){
		
	}
	
	public ActivityDAO getDao() {
		return activitydao;
	}

	public void setDao(ActivityDAO activitydao) {
		this.activitydao = activitydao;
	}
	
	@POST
	public void create(Activity activity) {
		activitydao.create(activity);
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") int id) {
		Activity activity = activitydao.findById(id);
		if (activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(activity).build();
	}

	@GET
	public List<Activity> listAll() {
		//@QueryParam("start") final Integer startPosition,
		//@QueryParam("max") final Integer maxResult
		List<Activity> activities = activitydao.findAll();
		return activities;
	}

	@PUT
	public Response update(Activity Activity){ 
		Response r = null;
		try{
			activitydao.update(Activity);
			r = Response.ok("OK").build();
		}
		catch(Exception e){
			System.out.println("exception in create "+e);
			r = Response.ok("error").build();
		}
		return r;
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") int id) {
		Response r = null;
		try{
			activitydao.delete(id);
			r = Response.ok("OK").build();
		}
		catch(Exception e){
			System.out.println("exception in create "+e);
			r = Response.ok("error").build();
		}
		return r;
	}

}