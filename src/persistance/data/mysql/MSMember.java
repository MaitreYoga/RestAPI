package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.Member;

public class MSMember extends Member
{

	public MSMember() {
	}

	private static final String table = "member";

	private static final String index = "id";
	private static final String joiningdate = "joiningdate";

	@Override
	public int getMemberId(String login) 
	{
		int memberId = 0;
		
		String request = "SELECT idmember FROM user WHERE login = '"+login+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
       
        try 
        {
			while(result.next())
			{
			    memberId = result.getInt("idmember");
			}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        
        return memberId;	
	}
	
	@Override
	public String getLogin(int seller) {
		String login = "";
		String request = "SELECT login FROM user WHERE idmember='"+seller+"'";
		
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
		try {
			while (result.next()){
				login = result.getString("login");
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login;

	}

	@Override
	public int getSeller(int idUser) {
		int seller = 0;
		String request = "SELECT idmember FROM user where id="+idUser;
		
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
		try {
			while (result.next()){
				seller = result.getInt("idmember");
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
	}

	@Override
	public int save(int userId, String date) {
		String request = "insert into "+table+"("+joiningdate+") values('"+ date +"')";
		int idmember = MySQLDatabase.getInstance().insertRequest(request);
		
		String request2 = "update user set idmember = "+idmember+" where id = "+userId;
		MySQLDatabase.getInstance().updateRequest(request2);		
		
		return idmember;
		
	}
}
