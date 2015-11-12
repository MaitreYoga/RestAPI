package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Period;

public class MSPeriod extends Period 
{

	private static final String table = "period";

	private static final String index = "id";
	private static final String startdate = "startdate";
	private static final String enddate = "enddate";
	
	List<Object> periodList = new ArrayList<Object>();

	@Override
	public List<Object> loadPeriod(String chosenEvent) 
	{		
		//get the period ID of the selected event
		String idPeriod = getIdPeriod(chosenEvent);
		
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+idPeriod+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				periodList.add(result.getString(startdate));  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        return periodList;
	}

	//method that gets the period ID corresponding to the chosen event's name
	private String getIdPeriod(String chosenEventName) 
	{
		String idPeriod = "";
        try
        {
        	String request = "SELECT * FROM event WHERE name ='"+chosenEventName+"'";
            ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
            while(result.next())
            {
               idPeriod = result.getString("idperiod").toString();
            }
        }
        catch(SQLException e1)
        {
            e1.printStackTrace();
        }
        
        return idPeriod;
	}

	@Override
	public String load(String periodID) 
	{
		String period= "";
		
		String request = "SELECT * FROM "+table+" WHERE "+index+" ='"+periodID+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        
        try 
        {
			while(result.next())
			{
			    period = "From "+result.getString(startdate)+" to "+ result.getString(enddate);
			  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return period;	
	}

}
