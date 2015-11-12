package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Manager;

public class MSManager extends Manager 
{
	List<Object> managerList = new ArrayList<Object>();

	public MSManager() 
	{
		super();
	}

	private static final String table = "manager";

	private static final String index = "id";

	@Override
	public String loadList() 
	{
		String managers = "        List of Managers: \n";
		String request = "SELECT idmanager, login from user where (idmanager is not null OR idmanager > 0);";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				String userName = MSUser.getUserName(result.getString("login"));
				managers =managers + userName +"\n" ;  
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        return managers;
	}

	@Override
	public boolean save(String user) 
	{
		String userLogin = MSUser.getUserLogin(user);
		try
		{
			String request ="select idmanager from user where login = '"+user+"'";
			ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
			result.next();
			if(result.getInt("idmanager") > 0)
				return false;
			request ="INSERT INTO "+table+" VALUES ()";
			int id = MySQLDatabase.getInstance().insertRequest(request);
			request ="update user set idmanager = "+id+" where login = '"+userLogin+"';";
			MySQLDatabase.getInstance().updateRequest(request);
			
			return id > 0;
		}
		 catch(Exception e)
        {
            e.printStackTrace();
        }
		return false;		
	}

	@Override
	public boolean delete(String userName) 
	{
		boolean answer = false;
		String login = MSUser.getUserLogin(userName);
		 try
         {
			 String request = "select idmanager FROM user WHERE login = '"+login+"'";
			 ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
			 int update = 0;
			 while(result.next())
				 update = result.getInt("idmanager");
			 
			 request = "update user set idmanager = NULL WHERE login = '"+login+"'";
			 MySQLDatabase.getInstance().updateRequest(request);
			 request = "DELETE FROM "+table+" WHERE "+index+" = '"+update+"'";
			 update =MySQLDatabase.getInstance().insertRequest(request);
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
				String userName = MSUser.getUserName(result.getString(index));
				managerList.add(userName);
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        return managerList;
	}

	@Override
	public void load(String login) {
		String request = "SELECT idmanager from user where login = '"+login+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				super.setID(result.getInt("idmanager"));
				super.setName(login);
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
	}
}
