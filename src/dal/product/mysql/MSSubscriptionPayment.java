package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.SubscriptionPayment;

public class MSSubscriptionPayment extends SubscriptionPayment {
	
	public MSSubscriptionPayment() {
		super();
	}

	private static final String table = "subscriptionpayment";

	private static final String index = "id";
	private static final String paymentdate = "paymentdate";
	private static final String amountpaid = "amountpaid";
	private static final String fkmember = "idmember";
	private static final String fkpaymenttype = "idpaymenttype";
	
	@Override
	public int save(int amountPaid, String paymentDate, String paymentType, int idMember) {
		
		String request = "insert into "+table+"("+index+", "+amountpaid+", "+paymentdate+", "+fkpaymenttype+") values('"+idMember+"','"+amountPaid+"','"+paymentDate+"','"+paymentType+"')";
		String request2 = "select MAX("+index+") from "+table+" where "+fkmember+"='"+idMember+"'";
		MySQLDatabase.getInstance().insertRequest(request);
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request2);
		
		int res = 0;
		try {
			while (result.next()){
				res = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void load(int idSubscriptionPayment) {
		String request = "SELECT * FROM "+table+" WHERE "+index+"='"+idSubscriptionPayment+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	try
		{
	    	while (result.next()) {
	    		super.setIdSubscriptionPayment(idSubscriptionPayment);
	    		super.setAmountPaid(result.getInt(amountpaid));
	    		super.setIdMember(result.getInt(fkmember));
	    		super.setPaymentType(result.getString(fkpaymenttype));
	    		super.setDatePayment(result.getString(paymentdate));
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
	public SubscriptionPayment getLastSubscriptionPayment(int idMember) {
		String request = "select MAX("+index+") as "+index+", "+fkmember+", "+amountpaid+", "+paymentdate+", "+fkpaymenttype+"  from "+table+" where "+fkmember+"='"+idMember+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	SubscriptionPayment subP = new MSSubscriptionPayment();
    	try
		{
	    	while (result.next()) {
	    		subP.setIdSubscriptionPayment(result.getInt(index));
	    		subP.setAmountPaid(result.getInt(amountpaid));
	    		subP.setIdMember(result.getInt(fkpaymenttype));
	    		subP.setPaymentType(result.getString(fkpaymenttype));
	    		subP.setDatePayment(result.getString(paymentdate));
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
    	
    	return subP;
	}

}
