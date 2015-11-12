package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.AdministratorList;

public class MSAdministratorList extends AdministratorList {

	@Override
	public List<Integer> load() {
		String request = "SELECT id FROM user where (idadministrator is not null OR idadministrator > 0)";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	        //Empty result => Wrong informations
			if(!result.next())
				return null;
			
	    	//Ouverture de session	    	
	    	result.beforeFirst();
	    	List<Integer> admins = new ArrayList<Integer>();
	    	while ( result.next() ) {
	    		admins.add(result.getInt("id"));
			}
		    /* On ferme le ResultSet */
		    result.close();

	    	return admins;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
		}
		return null;
	}
}