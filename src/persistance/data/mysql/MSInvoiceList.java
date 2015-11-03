package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.Administrator;
import persistance.data.generic.Invoice;
import persistance.data.generic.InvoiceList;
import persistance.data.generic.Notification;
import persistance.data.generic.NotificationList;
import persistance.data.generic.Order;
import persistance.data.generic.OrderList;

public class MSInvoiceList extends InvoiceList {

	private static final String table = "invoice";

	private static final String index = "id";
	private static final String amountpaid = "amountpaid";
	private static final String paymentdate = "paymentdate";
	private static final String fktype = "idpaymenttype";

	@Override
	public void load(int userId) {
		String request = "select i."+index+", i."+amountpaid+", i."+fktype+", i."+paymentdate+", o.id as idorder "
						+ "from "+table+" i, orders o where o.iduser = "+userId+" and i.id = o.idinvoice";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	result.beforeFirst();
	    	Invoice invoice;
	    	while ( result.next() ) {
	    		invoice = new MSInvoice();
	    		invoice.setIdInvoice(result.getInt(index));
	    		invoice.setAmountPaid(result.getInt(amountpaid));
	    		invoice.setPaymentType(result.getString(fktype));
	    		invoice.setPaymentDate(result.getString(paymentdate));
	    		invoice.setIdOrder(result.getInt("idorder"));
				super.add(invoice);
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
