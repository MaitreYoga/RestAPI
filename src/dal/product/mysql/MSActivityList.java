package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Activity;
import dal.product.generic.ActivityCategory;
import dal.product.generic.ActivityList;
import dal.product.generic.Manager;

public class MSActivityList extends ActivityList {

	public MSActivityList() {
		super();
	}

	private static final String table = "activity";


	private static final String index = "id";
	private static final String name = "name";
	private static final String shortdescription = "shortdescription";
	private static final String longdescription = "longdescription";
	private static final String fkcategory = "idactivitycategory";
	private static final String fkmanager = "idmanager";

	@Override
	public void load() {
		String request = "SELECT "+index+","+name+","+shortdescription+","+longdescription+",IFNULL("+fkmanager+",'') as "+fkmanager+",IFNULL("+fkcategory+",'') as "+fkcategory+" FROM "+table+";";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{			
	    	//Ouverture de session
			Activity activity;
			ActivityCategory catAct;
			Manager manager;
			String subquest1;
			ResultSet subresult1;
			String subquest2;
			ResultSet subresult2;
	    	result.beforeFirst();
	    	while ( result.next() ) {
	    		activity = new MSActivity();
	    		activity.setID(result.getString(index));
	    		activity.setName(result.getString(name));
	    		activity.setShortDescription(result.getString(shortdescription));
	    		activity.setDetailedDescription(result.getString(longdescription));
	    		if(!result.getString(fkmanager).equals("")) {
	    			subquest1 = "Select login from user where idmanager="+result.getString(fkmanager);
	    			subresult1 = MySQLDatabase.getInstance().selectRequest(subquest1);
	    			subresult1.next();
	    			manager = new MSManager();
		    		manager.setName(subresult1.getString("login"));
		    		activity.setManager(manager);
	    		}
	    		if(!result.getString(fkcategory).equals("")) {
	    			subquest2 = "Select name from activitycategory where id="+result.getString(fkcategory);
	    			subresult2 = MySQLDatabase.getInstance().selectRequest(subquest2);
	    			subresult2.next();
	    			catAct = new MSActivityCategory();
	    			catAct.setName(subresult2.getString("name"));
		    		activity.setCatAct(catAct);
	    		}
	    		super.add(activity);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage()+" In ActivityList");
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage()+" In ActivityList");
		}
	}
}
