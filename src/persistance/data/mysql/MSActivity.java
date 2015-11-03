package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.Activity;

public class MSActivity extends Activity {

	public MSActivity() {
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
	public void save() {
		try
		{
			String request ="INSERT INTO "+table+" ("+name+") VALUES ('"+super.getName()+"')";
			int result = MySQLDatabase.getInstance().insertRequest(request);
		}
		 catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public String load(String activityID) 
	{		
		String idCat = "";
		
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+activityID+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        
        try 
        {
			while(result.next())
			{
			    String eventActivity = result.getString(fkcategory);
			    //get the activity category of the selected activity
			    idCat = getCatName(eventActivity);
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return idCat;		
	}

	private String getCatName(String eventActivity) 
	{
		String CatName = "";
		
		String request = "SELECT "+name+" FROM activitycategory WHERE id ='"+fkcategory+"'";
		
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        
        try 
        {
			while(result.next())
			{
			    CatName = result.getString(name);
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return CatName;	
	}

	@Override
	public void update() {
		String request;
		if(super.getManager().getID() == 0)
			request = "update "+table+" set idmanager=NULL WHERE id ="+super.getID();
		else
			request = "update "+table+" set idmanager="+super.getManager().getID()+" WHERE id ="+super.getID();
		int result = MySQLDatabase.getInstance().updateRequest(request);
       
	}
	@Override
	public void updateName() {
		String request = "update "+table+" set name='"+super.getName()+"' WHERE id ="+super.getID();
		MySQLDatabase.getInstance().updateRequest(request);
	}

	@Override
	public void delete() {
		try
		{
			String request ="delete from "+table+" where id = "+super.getID();
			int result = MySQLDatabase.getInstance().deleteRequest(request);
		}
		 catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateCatAct() {
		String request;
		if(super.getCatAct().getID().equals(""))
			request = "update "+table+" set "+fkcategory+"=NULL WHERE id ="+super.getID();
		else
			request = "update "+table+" set "+fkcategory+"='"+super.getCatAct().getID()+"' WHERE id ="+super.getID();
		MySQLDatabase.getInstance().updateRequest(request);
	}
}
