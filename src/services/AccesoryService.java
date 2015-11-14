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
import dao.AccessoryDAO;
import model.Accessory;

@Path("accessories")
@Produces("application/json")
@Consumes("application/json")
public class AccesoryService {

	@EJB  
	AccessoryDAO accessorydao;
	
	public AccesoryService(){
		
	}
	
	public AccessoryDAO getDao() {
		return accessorydao;
	}

	public void setDao(AccessoryDAO accessorydao) {
		this.accessorydao = accessorydao;
	}
	
	@POST
	@Consumes("application/json")
	public void create(Accessory accessory) {
		accessorydao.create(accessory);
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") int id) {
		Accessory accessory = accessorydao.findById(id);
		if (accessory == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(accessory).build();
	}

	@GET
	public List<Accessory> listAll() {
		//@QueryParam("start") final Integer startPosition,
		//@QueryParam("max") final Integer maxResult
		List<Accessory> accessories = accessorydao.findAll();
		return accessories;
	}

	@PUT
	public Response update(Accessory accessory){ 
		Response r = null;
		try{
			accessorydao.update(accessory);
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
			accessorydao.delete(id);
			r = Response.ok("OK").build();
		}
		catch(Exception e){
			System.out.println("exception in create "+e);
			r = Response.ok("error").build();
		}
		return r;
	}

}