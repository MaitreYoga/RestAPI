package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.Session;
import dal.product.generic.Speaker;

public class MSSpeaker extends Speaker {
	
    public MSSpeaker(){
    	super();
    }

	private static final String table = "speaker";

	private static final String index = "id";
	private static final String job = "job";
	private static final String shortdescription = "shortdescription";
	private static final String detaileddescription = "detaileddescription";

	
    public MySQLDatabase mySQLDatabase;

	@Override
	public String save(String login) {
		String request = "insert into "+table+" ("+job+","+shortdescription+","+detaileddescription+") values ("
				+"'"+getJob()+"',"
				+"NULL,"
				+"NULL"
				+ ")";

		int id = MySQLDatabase.getInstance().insertRequest(request);		
				
		request = "update user set idspeaker = "+id+" where login = '"+login+"';";
		MySQLDatabase.getInstance().updateRequest(request);
		
		return null;
	}

	@Override
	public String load(int speakerID) 
	{				
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+speakerID+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{			    
				super.setJob(result.getString(job));
				super.setShortDescription(result.getString(shortdescription));
				super.setLongDescription(result.getString(detaileddescription));
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return "";		
	}

	@Override
	public void update(int speaker) {
		String job=super.getJob();
		String sDescription=super.getShortDescription();
		String lDescription=super.getLongDescription();
		
		String request = "update "+table+" set "+MSSpeaker.job+"='"+job+"', "+shortdescription+"='"+sDescription+"', "+detaileddescription+"='"+lDescription+"' where "+index+"='"+speaker+"'";

		MySQLDatabase.getInstance().insertRequest(request);
	}

	@Override
	public int getIdFromSpeaker(String user) {
		int speakerId = 0;
		
		String request = "SELECT idspeaker FROM user WHERE login ='"+user+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
       
        try 
        {
			while(result.next())
			{
			    speakerId = result.getInt("idspeaker");
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return speakerId;
	}

	@Override
	public String load(String speakerID) 
	{
		//String eventSpeaker = "anonymous"; 
		
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+speakerID+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
			    //eventSpeaker = result.getString("user");
			    
				super.setJob(result.getString(job));
				super.setShortDescription(result.getString(shortdescription));
				super.setLongDescription(result.getString(detaileddescription));
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return "";//eventSpeaker;	
	}
}
