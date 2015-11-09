package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.User;

public class MSUser extends User
{
	List<User> userList = new ArrayList<User>();

    public MSUser()
    {
    	super();
    }

	private static final String table = "user";

	private static final String index = "id";
	private static final String firstname = "firstname";
	private static final String lastname = "lastname";
	private static final String phonenumber = "phonenumber";
	private static final String mailadress = "mailadress";
	private static final String login = "login";
	private static final String password = "password";
	private static final String fkadress = "idadress";
	
	private static final String table2 = "adress";

	private static final String index2 = "id";
	private static final String number = "number";
	private static final String name = "name";
	private static final String postalcode = "postalcode";
	private static final String town = "town";
	
	@Override
	public String load(String login, String pwd) {
   	
		String request = "SELECT "+index+", "+firstname+","+lastname+","+phonenumber+","+mailadress+","+fkadress+","+MSUser.login+","+password+" FROM "+table+" where "+MSUser.login+"='"+login+"' and "+password+"='"+pwd+"'";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	        //Empty result => Wrong informations
			if(!result.next())
				return "Informations incorrectes. Veuillez réessayer";

	    	//Ouverture de session	    	
	    	result.beforeFirst();
	    	while ( result.next() ) {
	    		super.setId(result.getInt(index));
				super.setFirstName(result.getString(firstname));
				super.setLastName(result.getString(lastname));
				super.setPhone(result.getString(phonenumber));
				super.setMail(result.getString(mailadress));
				super.setLogin(result.getString(MSUser.login));
				super.setIdAdress(result.getInt(fkadress));
			}
	    	if(super.getIdAdress() > 0)
	    	{
		    	request = "SELECT "+number+","+name+","+postalcode+","+town+" FROM "+table2+" where "+index2+"='"+super.getIdAdress()+"'";
		    	
		    	result = MySQLDatabase.getInstance().selectRequest(request);
		    	
				try
				{
			        //Empty result => Wrong informations
					if(!result.next())
						return "Informations incorrectes. Veuillez réessayer";
					
			    	//Ouverture de session	    	
			    	result.beforeFirst();
			    	while ( result.next() ) {
						super.setNumber(result.getString(number));
						super.setName(result.getString(name));
						super.setPostalCode(result.getString(postalcode));
						super.setTown(result.getString(town));
			    	}
				}
				catch (SQLException e)
				{
					return e.getMessage();
				}
	    	}
	    	
		    /* On ferme le ResultSet */
		    result.close();

	    	return null;
		}
		catch (SQLException e)
		{
			return e.getMessage();
		}
		catch (NullPointerException e)
		{
			return e.getMessage();
		}
	}
	
	@Override
	public int loadId(String login) {
		String request = "select id from user where login ='"+login+"';";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{			
	    	//Ouverture de session	    	
	    	result.beforeFirst();
	    	while ( result.next() ) {
				return result.getInt(1);
	    	}
		}
		catch (SQLException e)
		{
			
		}
		return 0;
	}

	@Override
	public String save() {
		String request;
		int id = MySQLDatabase.ErrorCode;
		
		request = "insert into "+table+" (firstname, lastname, phonenumber, mailadress, login, password) values ("
							+"'"+getFirstName()+"',"
							+"'"+getLastName()+"',"
							+"'"+getPhone()+"',"
							+"'"+getMail()+"',"
							+"'"+getLogin()+"'"
							+ ")";
    	
    	MySQLDatabase.getInstance().insertRequest(request);

		if(getNumber() > 0)
		{
			request = "insert into "+table2+" values ("
					+"'"+getNumber()+"',"
					+"'"+getName()+"',"
					+"'"+getPostalCode()+"',"
					+"'"+getTown()+"'"
					+ ")";
			
			id = MySQLDatabase.getInstance().insertRequest(request);
			
			request = "update user set idadress = "+id+" where id="+getId();
			MySQLDatabase.getInstance().updateRequest(request);
		}
    	
    	return null;
	}
	
	@Override
	public int saveCurrentUser() {
		String request = "UPDATE "+table+" SET "
							+firstname+"='"+getFirstName()+"',"
							+lastname+"='"+getLastName()+"',"
							+phonenumber+"='"+getPhone()+"',"
							+mailadress+"='"+getMail()+"'"
							+" WHERE "+login+"='"+getLogin()+"'";
    	
    	int result = MySQLDatabase.getInstance().updateRequest(request);
    	
    	request = "UPDATE "+table2+" SET "
				+number+"="+getNumber()+","
				+name+"='"+getName()+"',"
				+postalcode+"="+getPostalCode()+","
				+town+"='"+getTown()+"'"
				+" WHERE "+index2+"='"+getIdAdress()+"'";

    	result = MySQLDatabase.getInstance().updateRequest(request);
    	
    	return result;
	}

	@Override
	public String check(String login) {
		
		String request = "SELECT "+MSUser.login+" FROM "+table+" where "+MSUser.login+"='"+login+"'";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
	        //Empty result => The user is not registered
			if(!result.next())
				return "User not registered";
			else
				return "User registered";
		}
		catch (SQLException e)
		{
			return e.getMessage();
		}
		catch (NullPointerException e)
		{
			return e.getMessage();
		}
	}

	@Override
	public List<User> load() 
	{
		 //String request = "SELECT * FROM user u, adress a where u.idadress = a.id";
		String request = "SELECT * FROM user u";
	        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
	        User u = resultToUser(result);
			while(u != null)
			{
				userList.add(u);
				u = resultToUser(result);
			}
	        return userList;		
	}

	public static String getUserLogin(String user) 
	{
		String userLogin = "";
		
		String request = "SELECT * FROM "+table+" where "+firstname+"='"+user+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				userLogin = result.getString(login);  
			} 
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
		return userLogin;
	}

	public static String getUserName(String userLogin) 
	{
		String userName = "";
		
		String request = "SELECT * FROM "+table+" where "+login+"='"+userLogin+"'";
        ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        try 
        {
			while(result.next())
			{
				userName = result.getString(firstname);  
			} 
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
		return userName;
	}

	@Override
	public User loadProfileById(int id) {
		
		//String request = "SELECT * FROM user u, adress a "
		//		+ "where u.idadress = a.id and u.id="+id+";";
		String request = "SELECT * FROM user u where u.id="+id+";";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        return resultToUser(result);
	}

	@Override
	public User loadProfileByLogin(String login) {
		//String request = "SELECT * FROM user u, adress a "
		//		+ "where u.idadress = a.id and u.login='"+login+"';";
		String request = "SELECT * FROM user where login='"+login+"';";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
        return resultToUser(result);
	}
	
	private static User resultToUser (ResultSet result)
	{
		try
		{
			if(result.next())
			{
				MSUser user = new MSUser();
				user.setId(result.getInt(index));
				user.setFirstName(result.getString(firstname));
				user.setLastName(result.getString(lastname));
				user.setLogin(result.getString(login));
				user.setMail(result.getString(mailadress));
				user.setPhone(result.getString(phonenumber));
				user.setConnectionToken("connectiontoken");
				//user.setIdAdress(result.getInt("number"));
				//user.setName(result.getString("name"));
				//user.setPostalCode(result.getString("postalcode"));
				//user.setTown(result.getString("town"));
				return user;
			}
			else
				return null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} 
	}
		
}