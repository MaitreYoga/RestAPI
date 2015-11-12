package dal.product.mysql;

import dal.MySQLDatabase;
import dal.product.generic.Inscription;

public class MSInscription extends Inscription 
{

	private static final String table = "inscription";

	private static final String fkstate = "idstate";
	private static final String fkmember = "idmember";
	private static final String fkevent = "idevent";

	public boolean save(int memberID, int chosenEvent, int amount) 
	{
		boolean answer = false;
		try
		{
			String request ="INSERT INTO "+table+" ("+fkmember+","+fkevent+","+fkstate+") VALUES ("+memberID+",'"+chosenEvent+"','"+amount+"$ paid')";
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
	public void save(int userID, String chosenEvent) {

	}
}
