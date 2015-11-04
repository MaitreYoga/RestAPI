package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Notification;
import dal.product.generic.NotificationList;

public class MSNotificationList extends NotificationList {

	private static final String table = "notification";

	private static final String index = "id";
	private static final String label = "label";
	private static final String isread = "isread";
	private static final String fkcreator = "idcreatedby";
	private static final String fkreceiver = "idreceivedby";

	@Override
	public void load(int idReceiver) {
		String request = "SELECT * FROM "+table+" WHERE "+fkreceiver+" = '"+idReceiver+"' AND "+isread+"=0";
    	
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	result.beforeFirst();
	    	Notification notif;
	    	while ( result.next() ) {
	    		notif = new MSNotification();
	    		notif.setID(Integer.parseInt(result.getString(index)));
	    		notif.setRead(result.getString(isread).equals("1"));
	    		notif.setMessage(result.getString(label));
	    		notif.setSender(result.getString(fkcreator));
	    		notif.addReceiver(result.getString(fkreceiver));
				super.add(notif);
			}
		    /* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}
}
