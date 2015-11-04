package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import dal.MySQLDatabase;
import dal.product.generic.Invoice;
import dal.product.generic.Order;
import dal.product.generic.OrderLine;

public class MSInvoice extends Invoice {
	
	public MSInvoice() {
		super();
	}

	private static final String table = "invoice";

	private static final String index = "id";
	private static final String amountpaid = "amountpaid";
	private static final String paymentdate = "paymentdate";
	private static final String fktype = "idpaymenttype";
	
	@Override
	public int save(int amountPaid, String paymentDate, String paymentType, int idOrder) {
		
		String request = "insert into "+table+"("+amountpaid+","+fktype+","+paymentdate+") values('"+amountPaid+"','"+paymentType+"','"+paymentDate+"')";
		MySQLDatabase.getInstance().insertRequest(request);
		
		String request2 = "select MAX("+index+") as "+index+" from "+table+" where idOrder='"+idOrder+"'";
				
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
		
		String request3 = "update orders set idinvoice = "+res+" where id='"+idOrder+"'";
		MySQLDatabase.getInstance().updateRequest(request3);
		
		return res;
	}

	@Override
	public void load(int idInvoice) {
		String request = "SELECT * FROM "+table+" WHERE "+index+"='"+idInvoice+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	try
		{
	    	while (result.next()) {
	    		super.setIdInvoice(idInvoice);
	    		super.setAmountPaid(result.getInt(amountpaid));
	    		super.setPaymentDate(result.getString(paymentdate));
	    		super.setPaymentType(result.getString(fktype));
	    		//super.setIdOrder(result.getInt("order"));
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
