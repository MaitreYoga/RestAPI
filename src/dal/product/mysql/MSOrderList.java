package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Order;
import dal.product.generic.OrderList;

public class MSOrderList extends OrderList {

	private static final String index = "id";
	private static final String isvalidated = "isvalidated";

	@Override
	public void load(int userId) {
		String request = "SELECT id , isvalidated FROM orders WHERE iduser = "+userId;
		//String request = "SELECT "+index+" , "+isvalidated+" FROM "+table+" WHERE "+fkuser+" = "+userId;
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	result.beforeFirst();
	    	Order order;
	    	while ( result.next() ) {
	    		order = new MSOrder();
	    		order.setIdOrder(result.getInt(index));
	    		order.setStateOrder(result.getBoolean(isvalidated) ? "Validated" : "Not validated");
	    		//order.setDateOrder(result.getString("dateOrder"));
				super.add(order);
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
