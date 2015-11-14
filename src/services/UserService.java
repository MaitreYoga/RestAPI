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

import dao.interfaceDAO.UserDAO;
import model.User;

@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class UserService {

	@EJB
	UserDAO userdao;
	
	public UserService(){
		
	}
	
	public UserDAO getDao() {
		return userdao;
	}

	public void setDao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	@POST
	public void create(User user) {
		userdao.create(user);
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") int id) {
		User user = userdao.findById(id);
		if (user == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	@GET
	public List<User> listAll() {
		//@QueryParam("start") final Integer startPosition,
		//@QueryParam("max") final Integer maxResult
		List<User> users = userdao.findAll();
		return users;
	}

	@PUT
	public Response update(User user){ 
		Response r = null;
		try{
			userdao.update(user);
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
			userdao.delete(id);
			r = Response.ok("OK").build();
		}
		catch(Exception e){
			System.out.println("exception in create "+e);
			r = Response.ok("error").build();
		}
		return r;
	}

}