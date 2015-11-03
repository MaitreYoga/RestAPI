package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.ActivityCategory;
import persistance.data.generic.ActivityCategoryList;

public class MSActivityCategoryList extends ActivityCategoryList {

	public MSActivityCategoryList() {
		super();
	}

	private static final String table = "activitycategory";

	private static final String index = "id";
	private static final String name = "name";
	//private static final String fkparentcategory = "idactivitycategory";

	@Override
	public void load() {
		String request = "SELECT "+index+", "+name+" FROM "+table+";";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	    	//Ouverture de session
	    	result.beforeFirst();
	    	ActivityCategory catAct;
	    	while (result.next()) {
	    		catAct = new MSActivityCategory();
	    		catAct.setID(result.getString(index));
	    		catAct.setName(result.getString(name));
				super.add(catAct);
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
