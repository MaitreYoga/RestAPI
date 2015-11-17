package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Speaker;
import dal.product.generic.SpeakerList;

public class MSSpeakerList extends SpeakerList {

	public MSSpeakerList() {
		super();
	}

	private static final String table = "speaker";

	private static final String job = "job";
	private static final String shortdescription = "shortdescription";
	private static final String detaileddescription = "detaileddescription";

	@Override
	public void load() {
		String request = "SELECT s.id, u.firstname, u.lastname, s."+job+", s."+shortdescription+", s."+detaileddescription+" FROM user u, "+table+" s where (s.id is not null and s.id > 0) and u.idspeaker = s.id;";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{			
	    	//Ouverture de session
			Speaker speaker;
	    	result.beforeFirst();
	    	while ( result.next() ) {
	    		speaker = new MSSpeaker();
	    		speaker.setId(result.getInt("id"));
	    		speaker.setJob(result.getString(job));
	    		speaker.setShortDescription(result.getString(shortdescription));
	    		speaker.setLongDescription(result.getString(detaileddescription));
	    		speaker.setName(result.getString("firstname") + " " +  result.getString("lastname"));
	    		super.add(speaker);
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
