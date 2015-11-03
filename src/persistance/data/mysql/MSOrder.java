package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import persistance.MySQLDatabase;
import persistance.data.generic.Order;
import persistance.data.generic.OrderLine;

public class MSOrder extends Order {
	
	public MSOrder() {
		super();
	}

	private static final String table = "orders";

	private static final String index = "id";
	private static final String isvalidated = "isvalidated";
	private static final String fkinvoice = "idinvoice";
	private static final String fkuser = "iduser";
	
	@Override
	public int save(int userId, String state, String orderDate) {
		
		String request = "insert into "+table+"("+isvalidated+","+fkuser+") values('"+state+"','"+userId+"')";
		int res = MySQLDatabase.getInstance().insertRequest(request);
		return res;
	}

	@Override
	public void load(int idOrder) {
		String request = "SELECT * FROM "+table+" WHERE "+index+"='"+idOrder+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	try
		{
	    	while (result.next()) {
	    		super.setIdOrder(idOrder);
	    		super.setStateOrder(result.getString(isvalidated));
	    		super.setBuyer(result.getString(fkuser));
	    		//super.setDateOrder(result.getString("dateOrder"));
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

	@Override
	public void updateStateOrder(int idOrder, String state) {
		String request = "update "+table+" set "+isvalidated+"='"+ state +"' where "+index+"='"+idOrder+"'";
		MySQLDatabase.getInstance().updateRequest(request);
		
	}

}
