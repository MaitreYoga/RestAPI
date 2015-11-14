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

import dao.interfaceDAO.ProductDAO;
import model.Product;

@Path("products")
@Produces("application/json")
@Consumes("application/json")
public class ProductService {

	@EJB
	ProductDAO productdao;
	
	public ProductService(){
		
	}
	
	public ProductDAO getDao() {
		return productdao;
	}

	public void setDao(ProductDAO productdao) {
		this.productdao = productdao;
	}
	
	@POST
	public void create(Product product) {
		productdao.create(product);
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") int id) {
		Product product = productdao.findById(id);
		if (product == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(product).build();
	}

	@GET
	public List<Product> listAll() {
		//@QueryParam("start") final Integer startPosition,
		//@QueryParam("max") final Integer maxResult
		List<Product> products = productdao.findAll();
		return products;
	}

	@PUT
	public Response update(Product product){ 
		Response r = null;
		try{
			productdao.update(product);
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
			productdao.delete(id);
			r = Response.ok("OK").build();
		}
		catch(Exception e){
			System.out.println("exception in create "+e);
			r = Response.ok("error").build();
		}
		return r;
	}

}