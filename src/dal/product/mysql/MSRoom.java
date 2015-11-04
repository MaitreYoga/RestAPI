package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Room;

public class MSRoom extends Room {

	public MSRoom(int idRoom, String name, int surface, int capacity) {
		super(idRoom, name, surface, capacity);
	}
	
	public MSRoom(){
		super();
	}

	private static final String table = "room";

	private static final String index = "id";
	private static final String name = "name";
	private static final String surface = "surface";


	@Override
	public String load(String placeID) 
	{
		String place= "";
		
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+placeID+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request); 
        try 
        {
			while(result.next())
			{
			    place = result.getString(name);
			  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return place;	
	}

	@Override
	public List<Room> load()
	{
		List<Room> list = new ArrayList<Room>();
		String request = "SELECT * FROM "+table+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				list.add(new MSRoom(result.getInt(index),result.getString(name),result.getInt(surface),0));
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public boolean createRoom(String room,int surface) 
	{
		boolean answer = false;
		try
		{
			String request ="INSERT INTO "+table+" ("+name+","+MSRoom.surface+") VALUES ('"+room+"','"+surface+"')";
			int result = MySQLDatabase.getInstance().insertRequest(request);
			if(result > 0)
			{
				answer = true;
			}
			else
			{
				answer = false;
			}
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
		return answer;
	}

	@Override
	public String loadList() 
	{
		String rooms = "         List of Rooms: \n";
		String request = "SELECT * FROM "+table+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				rooms =rooms + result.getString(name)+"\n" ;  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public boolean deleteRoom(String roomName) 
	{
		boolean answer = false;
		try
        {
			String request = "DELETE FROM "+table+" WHERE  "+name+" = '"+roomName+"'";
			int update =MySQLDatabase.getInstance().deleteRequest(request);
			if(update != 0)
			{
				answer = true;
			}
			else if(update == 0)
			{
				answer = false;
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }	
		return answer;
	}

	@Override
	public int deleteAccessoryFromRoom(int idRoom, int idAccessory) {
		int update = 0;
		try
        {
			String request = "DELETE FROM accessoryavailability WHERE id="+idRoom+" AND idaccessory="+idAccessory;
			update =MySQLDatabase.getInstance().deleteRequest(request);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }	
		return update;
	}

	@Override
	public int addAccessoryToRoom(int idAccessory, int idRoom2) {
		int result=0;
		try
		{
			String request ="INSERT INTO accessoryavailability (id,idaccessory) VALUES ("+idRoom2+","+idAccessory+")";
			result = MySQLDatabase.getInstance().insertRequest(request);
		}
		catch(Exception e)
        {
            e.printStackTrace();
        }
		return result;
	}

}
