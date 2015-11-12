package services;

import java.io.InputStream;
import java.util.HashMap;

import javax.management.MXBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import bl.facade.UserFacade;
import dal.product.generic.User;
import utilitaries.Rest;

@Path("users2")
@Produces("application/json")
@Consumes("application/json")
public class Users2 {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Users2() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of Users2
     * @return an instance of String
     */
    @GET
    @Path("{id}")
    public Object getUser(@PathParam("id") String id) {
        // TODO return proper representation object
    	User user;
        if((user=UserFacade.instance().getUser(Integer.parseInt(id)))!=null)
        {
        	return user;
        }
        else {
        	return null;
        }
        
    }
   /* 
    @PUT
    @Path("{id}")
    public Object updateUser(@PathParam("id") int id, User user)
    {
    	return UserFacade.instance().getUser(id);
    	
   
    }

    /**
     * PUT method for updating or creating an instance of Users2
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */


}