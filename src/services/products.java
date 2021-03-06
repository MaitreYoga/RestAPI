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
import bl.facade.ShopFacade;
import dal.product.generic.Product;

@Path("products")
@Produces("application/json")
@Consumes("application/json")
public class products {
	
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public products() {

    }

    /**
     * Retrieves representation of an instance of Test3
     * @return an instance of String
     */
    @GET
    public List<Product> getProducts() {
    	return ShopFacade.instance().getProducts();
    }

	@GET
	@Path("{id}")
	public Product getProduct(@PathParam("id") String id) {
		return ShopFacade.instance().getProduct(Integer.parseInt(id));
	}
	
	//ShopFacade.instance().getProductsByCat(Integer.parseInt(map.get("cat"))
	
    /**
     * PUT method for updating or creating an instance of Test3
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    public void putJson(String content) {
    }

}

