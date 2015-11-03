package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistance.MySQLDatabase;
import persistance.data.generic.ProductLine;
import persistance.data.generic.ProductLineList;

public class MSProductLineList extends ProductLineList {

	private static final String table = "productline";

	private static final String index = "id";
	private static final String quantity = "quantity";
	private static final String fkshoppingcart = "idshoppingcart";
	private static final String fkorder = "idorder";
	private static final String fkproduct = "idproduct";
	
    private List<MSProductLine> mSProductLine = new ArrayList<MSProductLine> ();
    
	public void load(int userId) {
		String request = "SELECT SL."+index+", P.name, SL."+quantity+", P.price, P.memberreduction FROM shoppingcart S, "+table+" SL, product P WHERE S.iduser = "+userId+" and SL."+fkshoppingcart+"=S.id and P.id=SL."+fkproduct+";";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	//result.beforeFirst();
	    	ProductLine prod;
	    	while (result.next()) {
	    		prod = new MSProductLine();
	    		prod.setIdProduct(result.getInt("id"));
	    		prod.setProdName(result.getString("name"));
	    		prod.setQuantity(Integer.parseInt(result.getString(quantity)));
	    		prod.setUnitPrice(Integer.parseInt(result.getString("price")));
	    		if (result.getString("memberprice") != null){
	    			prod.setMemberPrice(result.getInt("memberreduction"));
	    		} else {
	    			prod.setMemberPrice(Integer.parseInt(result.getString("price")));
	    		}
				super.add(prod);
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

	public void deleteProd(int product, int userId) {
		int idCart = 0; // obligé d'initialiser car dans if
		
		String request1 = "SELECT id FROM shoppingcart WHERE iduser='"+userId+"'";
		ResultSet result1 = MySQLDatabase.getInstance().selectRequest(request1);
		try {
			while (result1.next()){
				idCart = result1.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String request2 = "DELETE FROM "+table+" WHERE "+fkshoppingcart+"='"+idCart+"' and "+fkproduct+"='"+product+"'";
		MySQLDatabase.getInstance().deleteRequest(request2);
	}

	public void updateProd(int product, int quantity, int userId) {
		int idCart = 0; // obligé d'initialiser car dans if
		
		String request1 = "SELECT id FROM shoppingcart WHERE iduser='"+userId+"'";
		ResultSet result1 = MySQLDatabase.getInstance().selectRequest(request1);
		try {
			while (result1.next()){
				idCart = result1.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String request2 = "UPDATE "+table+" SET quantity='"+quantity+"' WHERE id='"+idCart+"' and fkidproduct='"+product+"'";
		int result2 = MySQLDatabase.getInstance().updateRequest(request2);
	}
}
