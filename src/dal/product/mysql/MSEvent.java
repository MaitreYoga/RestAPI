package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Event;

public class MSEvent extends Event 
{
    
	public MSEvent(){
		super();
	}
	
	public MSEvent(int id, String name, int price, String activity, String startperiod, String endperiod , String room,  String speaker){
		super();
		setId(id);
		setName(name);
	    setPrice(price);
	    setActivity(activity);
	    setStartDate(startperiod);
	    setEndDate(endperiod);
	    setPlace(room);
	    setSpeaker(speaker);    
	}
	
	private static final String table = "event";

	private static final String index = "id";
	private static final String name = "name";
	private static final String price = "price";
	private static final String fkactivity = "idactivity";
	private static final String fkperiod = "idperiod";
	private static final String fkroom = "idroom";
	private static final String fkspeaker = "idspeaker";

	@Override
	public String save() {
		try {
		//Get the max id of Period to get the new ID
		String request1 = "Select max(id) as id from period;";
		ResultSet result1 = MySQLDatabase.getInstance().selectRequest(request1);
		result1.next();
		int id = 1+(Integer.parseInt(result1.getString(index)));

		//Create the Period
		String request2 = "insert into period values ("+id+",'"+super.getStartDate()+"','"+super.getEndDate()+"')";
		MySQLDatabase.getInstance().insertRequest(request2);
		
		//Create the event
		String request3 = "insert into "+table+" (name,price,idroom,idperiod,idactivity,idspeaker) values "
				+ "('"+super.getName()+"', "
				+ ""+super.getPrice()+", "
				+ ""+super.getRoom()+", "
				+ ""+id+", "
				+ ""+super.getActivity()+", "
				+ ""+super.getSpeaker()+");";
		MySQLDatabase.getInstance().insertRequest(request3);
		}
		catch (NumberFormatException | SQLException e) {
			System.err.println(e.getMessage());
			return "There was a problem while trying to create the event";
		}
		return "Event created !";
	}

	@Override
	public Event load(int eventId) 
	{				
		String request = "SELECT * FROM event WHERE id ="+eventId+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
                
        try 
        {
			while(result.next())
			{
				setId(eventId);
			   setName(result.getString(name));
			   setPrice(Integer.parseInt(result.getString(price)));
			   setActivity(result.getString(fkactivity));
			   setPeriod(result.getString(fkperiod));
			   setPlace(result.getString(fkroom));
			   setSpeaker(result.getString(fkspeaker));      
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return this;	
	}
}
