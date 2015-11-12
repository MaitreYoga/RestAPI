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
import dal.product.generic.Event;

@Path("events")
@Produces("application/json")
@Consumes("application/json")
public class Events {
	
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Events() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of events
     * @return an instance of a List of event
     */
    @GET
    public List<Event> getEvents() {
    	
    	return EventFacade.instance().getAllEvents();
    }

	@GET
	@Path("{id}")
	public Event getEvent(@PathParam("id") String id) {
		//TODO method
		return EventFacade.instance().getAllEvents().get(0);
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
