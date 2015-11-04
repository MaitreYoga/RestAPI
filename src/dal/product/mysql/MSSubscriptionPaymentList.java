package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.SubscriptionPayment;
import dal.product.generic.SubscriptionPaymentList;

public class MSSubscriptionPaymentList extends SubscriptionPaymentList {

	private static final String table = "subscriptionpayment";

	private static final String index = "id";
	private static final String paymentdate = "paymentdate";
	private static final String amountpaid = "amountpaid";
	private static final String fkmember = "idmember";
	private static final String fkpaymenttype = "idpaymenttype";
	
	@Override
	public void load(int idMember) {
		String request = "SELECT * FROM "+table+" WHERE "+fkmember+" = '"+idMember+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
    	
		try
		{
			//Ouverture de session	    	
	    	result.beforeFirst();
	    	SubscriptionPayment subPayment;
	    	while ( result.next() ) {
	    		subPayment = new MSSubscriptionPayment();
	    		subPayment.setIdSubscriptionPayment(result.getInt(index));
	    		subPayment.setAmountPaid(result.getInt(amountpaid));
	    		subPayment.setIdMember(idMember);
	    		subPayment.setPaymentType(result.getString(fkpaymenttype));
	    		subPayment.setDatePayment(result.getString(paymentdate));
				super.add(subPayment);
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

