package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import persistance.MySQLDatabase;
import persistance.data.generic.ActivityCategory;

public class MSActivityCategory extends ActivityCategory 
{
	public List<Object> categories = new ArrayList<Object>();

	private static final String table = "activitycategory";

	private static final String index = "id";
	private static final String name = "name";
	
	@Override
	public boolean save(String activityCategoryName) 
	{
		boolean answer = false;
		try
		{
			String request ="INSERT INTO "+table+" ("+name+") VALUES ('"+activityCategoryName+"')";
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
		String categories = "        Activity Category List: \n";
		String request = "SELECT * FROM "+table+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				categories =categories + result.getString(name)+"\n" ;  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        return categories;
		
	}

	@Override
	public boolean delete(String activityCategoryName) 
	{
		boolean answer = false;
		 try
        {
			 String request = "DELETE FROM "+table+" WHERE  "+name+" = '"+activityCategoryName+"'";
			 int update =MySQLDatabase.getInstance().insertRequest(request);
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
	public List<Object> load() 
	{
		String request = "SELECT * FROM "+table+"";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				categories.add(result.getString(name));    
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
		return categories;		
	}

}
