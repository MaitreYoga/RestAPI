package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Room;
import dal.product.generic.RoomList;

public class MSRoomList extends RoomList {

	public MSRoomList() {
		super();
	}

	private static final String table = "room";

	private static final String index = "id";
	private static final String name = "name";
	private static final String surface = "surface";

	@Override
	public void load() {
		String request = "SELECT "+index+","+name+","+surface+" FROM "+table+";";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	    	//Ouverture de session
			Room room;
	    	result.beforeFirst();
	    	while ( result.next() ) {
	    		room = new MSRoom();
	    		room.setIdRoom(Integer.parseInt(result.getString(index)));
	    		room.setName(result.getString(name));
	    		if(result.getString(surface) != null)
	    			room.setSurface(Integer.parseInt(result.getString(surface)));
	    		/*if(result.getString("nbMaxParticipant") != null)
	    			room.setCapacity(Integer.parseInt(result.getString("nbMaxParticipant")));*/
	    		super.add(room);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage()+" In SpeakerList");
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage()+" In SpeakerList");
		}
	}
}
