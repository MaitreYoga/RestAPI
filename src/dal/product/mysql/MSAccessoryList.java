package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Accessory;
import dal.product.generic.AccessoryList;

public class MSAccessoryList extends AccessoryList {

	public MSAccessoryList() {
		super();
	}
	
	private static final String table = "accessory";
	
	private static final String index = "id";
	private static final String description = "description";

	@Override
	public void load() {
		String request = "SELECT id, description FROM accessory";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	    	//Ouverture de session
	    	result.beforeFirst();
	    	Accessory access;
	    	while (result.next()) {
	    		access = new MSAccessory();
	    		access.setID(result.getString("id"));
	    		access.setDescription(result.getString("description"));
				super.add(access);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
