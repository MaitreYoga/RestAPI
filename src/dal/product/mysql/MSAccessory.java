package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dal.MySQLDatabase;
import dal.product.generic.Accessory;

public class MSAccessory extends Accessory {

	public MSAccessory(){
		super();
	}

	public MSAccessory(String description, int id) {
		super(description, id);
	}
	
	private static final String table = "accessory";
	
	private static final String description = "description";

	@Override
	public void save() {
		try
		{
			String request ="INSERT INTO "+table+" ("+description+") VALUES ('"+super.getDescription()+"')";
			MySQLDatabase.getInstance().insertRequest(request);
		}
		 catch(Exception e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void update() {
		try
		{
			String request ="Update "+table+" set "+description+" = '"+super.getDescription()+"' where id = "+super.getID();
			MySQLDatabase.getInstance().updateRequest(request);
		}
		 catch(Exception e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void delete() {
		try
		{
			String request ="delete from "+table+" where id = "+super.getID();
			MySQLDatabase.getInstance().deleteRequest(request);
		}
		 catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	@Override
	public ArrayList<Accessory> getAccessoriesByRoom(int idRoom) {
		String request = "SELECT a.description, a.id FROM "+table+" a, accessoryavailability r WHERE r.idaccessory = a.id AND r.id="+idRoom;
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	ArrayList<Accessory> list = new ArrayList<Accessory>();
		try
		{
	    	//Ouverture de session
	    	result.beforeFirst();
	    	while (result.next()) {
	    		list.add(new MSAccessory(result.getString("description"),result.getInt("id")));
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
		
		return list;
	}
}
