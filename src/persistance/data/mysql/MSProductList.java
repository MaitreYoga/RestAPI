package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.Product;
import persistance.data.generic.ProductList;

public class MSProductList extends ProductList {

	private static final String table = "product";

	private static final String index = "id";
	private static final String name = "name";
	private static final String price = "price";
	private static final String quantityavailable = "quantityavailable";
	private static final String memberreduction = "memberreduction";
	private static final String urlimage = "urlimage";
	private static final String fkbrand = "idbrand";
	private static final String fkseller = "idseller";
	private static final String fkcategory = "idcategory";

	@Override
	public void load(int idMember) {
		System.out.println(idMember);
		String request = "SELECT * FROM "+table+" WHERE "+fkseller+" = '"+idMember+"'";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	result.beforeFirst();
	    	Product product;
	    	while ( result.next() ) {
	    		product = new MSProduct();
	    		product.setIdProd(result.getInt(index));
	    		product.setName(result.getString(name));
	    		product.setBrand(result.getString(fkbrand));
	    		product.setPrice(result.getInt(price));
	    		product.setQuantityAvailable(result.getInt(quantityavailable));
	    		if (result.getString(memberreduction) != null){
	    			product.setMemberPrice(result.getInt(memberreduction));
	    		} else{
	    			product.setMemberPrice(result.getInt(price));
	    		}
	    		product.setProductCat(result.getInt(fkcategory));
	    		product.setSeller(result.getInt(fkseller));
	    		product.setUrlImage(result.getString(urlimage));
	    		super.add(product);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}

}
