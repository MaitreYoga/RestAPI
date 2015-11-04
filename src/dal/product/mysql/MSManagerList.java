package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Manager;
import dal.product.generic.ManagerList;

public class MSManagerList extends ManagerList {
	
	public MSManagerList() {
		super();
	}

	@Override
	public void load() {
		String request = "SELECT idmanager, login from user where (idmanager is not null OR idmanager > 0);";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{			
	    	//Ouverture de session
			Manager manager;
	    	result.beforeFirst();
	    	while ( result.next() ) {
	    		manager = new MSManager();
	    		manager.setID(result.getInt("idmanager"));
	    		manager.setName(result.getString("login"));
	    		super.add(manager);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage()+" In ManagerList");
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage()+" In ManagerList");
		}
	}
}
