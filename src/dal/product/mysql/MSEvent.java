package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import dal.MySQLDatabase;
import dal.product.generic.Event;

public class MSEvent extends Event 
{
    
	public MSEvent(){
		super();
	}
	
	public MSEvent(String name, int price, String activity, String startperiod, String endperiod , String room,  String speaker){
		super();
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
		int result2 = MySQLDatabase.getInstance().insertRequest(request2);
		
		//Create the event
		String request3 = "insert into "+table+" (name,price,idroom,idperiod,idactivity,idspeaker) values "
				+ "('"+super.getName()+"', "
				+ ""+super.getPrice()+", "
				+ ""+super.getRoom()+", "
				+ ""+id+", "
				+ ""+super.getActivity()+", "
				+ ""+super.getSpeaker()+");";
		int result3 = MySQLDatabase.getInstance().insertRequest(request3);
		}
		catch (NumberFormatException | SQLException e) {
			System.err.println(e.getMessage());
			return "There was a problem while trying to create the event";
		}
		return "Event created !";
	}

	@Override
	public MSEvent load(String chosenEvent) 
	{
		//get the event ID of the selected event
		int idEvent = getIdEvent(chosenEvent);
				
		String request = "SELECT * FROM event WHERE id ="+idEvent+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
                
        try 
        {
			while(result.next())
			{
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

	public int getIdEvent(String chosenEvent) 
	{
		int idEvent = 0;
        try
        {
        	String request = "SELECT id FROM "+table+" WHERE name ='"+chosenEvent+"'";
            ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
            while(result.next())
            {
               idEvent = result.getInt("id");
            }
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }
        
        return idEvent;
	}
}
