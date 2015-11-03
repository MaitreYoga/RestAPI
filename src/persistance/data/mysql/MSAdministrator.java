package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.Administrator;

public class MSAdministrator extends Administrator {

	@Override
	public void load(String login) {
		String request = "SELECT idadministrator from user where login = '"+login+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				super.setID(result.getInt("idadministrator"));
				super.setUser(login);
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
	}
}