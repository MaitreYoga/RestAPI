package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import bl.facade.EventFacade;
import dal.product.generic.Accessory;

@Path("accessories")
@Produces("application/json")
@Consumes("application/json")
public class Accessories {
	
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Accessories() {
    	
    }

    /**
     * Retrieves representation of an instance of Test3
     * @return an instance of String
     */
    @GET
    public List<Accessory> getAccessories() {
    	
    	return EventFacade.instance().getAllAccessories().getList();
    }

	@GET
	@Path("{id}")
	public Accessory getAccessory(@PathParam("id") String id) {
		return EventFacade.instance().getAllAccessories().getList().get(0);
	}
    /**
     * PUT method for updating or creating an instance of Test3
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    public void putJson(String content) {
    	
    }
}
