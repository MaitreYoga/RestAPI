package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.OrderLine;
import dal.product.generic.OrderLineList;

public class MSOrderLineList extends OrderLineList {

	private static final String table = "productline";

	//private static final String index = "id";
	private static final String quantity = "quantity";
	//private static final String fkshoppingcart = "idshoppingcart";
	private static final String fkorder = "idorder";
	private static final String fkproduct = "idproduct";
	
    //private List<MSOrderLine> mSOrderLine = new ArrayList<MSOrderLine> ();
    
	public void load(int orderId) {
		String request = "SELECT * FROM "+table+" WHERE "+fkorder+"='"+orderId+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	try
		{
	    	OrderLine orderL;
	    	while (result.next()) {
	    		orderL = new MSOrderLine();
	    		orderL.setIdOrder(orderId);
	    		orderL.setIdProduct(result.getInt(fkproduct));
	    		orderL.setQuantity(result.getInt(quantity));
				super.add(orderL);
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

	public void deleteProd(int order) {
		String request = "DELETE FROM "+table+" WHERE id='"+order+"'";
		MySQLDatabase.getInstance().selectRequest(request);

		String request2 = "DELETE FROM orders WHERE id='"+order+"'";
		MySQLDatabase.getInstance().deleteRequest(request2);
	}


}
