package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Event;
import dal.product.generic.EventList;

public class MSEventList extends EventList 
{
	public List<Event> event = new ArrayList<Event> ();
	public List<Object> events = new ArrayList<Object>();
	public List<Object> allEvents = new ArrayList<Object>();

	private static final String table = "event";

	private static final String name = "name";
	private static final String price = "price";
	private static final String fkactivity = "idactivity";
	private static final String fkroom = "idroom";
	private static final String fkspeaker = "idspeaker";

	@Override
	public void load() 
	{
		String request = "SELECT * "
				+ "FROM "+table+" e , period p "+
				"Where e.idperiod = p.id ;";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try 
		{
			while(result.next())
			{
				Event event = new MSEvent(result.getString(name), result.getInt(price),
						result.getString(fkactivity), result.getString("startdate"), result.getString("enddate"), result.getString(fkroom), 
						result.getString(fkspeaker));
				this.add(event);    
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public void load(int userID) {
		String request = "SELECT e.name as nameevent , e.price , r.name as nameroom , i.idstate , p.startdate , p.enddate , a.name as nameactivity , IFNULL(e.idspeaker,'No Speaker') as speaker "
				+"FROM event e , inscription i , room r , activity a , member m , period p "
				+"WHERE i.idevent = e.id AND "
				+"i.idmember = m.id AND "
				+"e.idroom = r.id AND "
				+"e.idactivity = a.id AND "
				+"e.idperiod = p.id;";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try 
		{
			Event e;
			while(result.next())
			{
				e = new MSEvent();
				e.setName(result.getString("nameevent"));
				e.setPrice(Integer.parseInt(result.getString("price")));
				e.setRoom(result.getString("nameroom"));
				e.setState(result.getString("idstate"));
				e.setStartDate(result.getString("startdate"));
				e.setEndDate(result.getString("enddate"));
				e.setActivity(result.getString("nameactivity"));
				
				if(!result.getString("speaker").equals("No Speaker"))
				{
					e.setSpeaker(result.getString("speaker"));
				}
				else{
					e.setSpeaker("No speaker");
				}
				add(e);
			}
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

	@Override
	public List<Object> loadList(String searchCriteria) 
	{
		String request = "SELECT * FROM event";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try 
		{
			while(result.next())
			{
				 events.add(result.getString(searchCriteria)); 
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return events;
	}

	@Override
	public String loadEvents(String searchCriteria,String choice) 
	{
		String eventList = "";
		String request ="";
		
		if (choice=="name")
		{
			request = "SELECT * FROM event WHERE name ='"+searchCriteria+"'";
		}
		if (choice=="speaker")
		{
			request = "SELECT * FROM event WHERE idspeaker ='"+searchCriteria+"'";
		}
		if (choice=="activity")
		{
			request = "SELECT * FROM event WHERE idactivity ='"+searchCriteria+"'";
		}
		if (choice=="place")
		{
			request = "SELECT * FROM event WHERE idroom ='"+searchCriteria+"'";
		}
		if (choice=="period")
		{
			request = "SELECT * FROM event WHERE idperiod ='"+searchCriteria+"'";		
		}
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try 
		{
			while(result.next())
			{
				 eventList = eventList + result.getString("name") + "\n"; 
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return eventList;
	}
}