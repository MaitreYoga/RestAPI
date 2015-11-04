package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class InvoiceList {

	private List<Invoice> invoices;
	
	public InvoiceList(){
		invoices = new ArrayList<Invoice>();
	}
	
	public int size(){
		return invoices.size();
	}
	
	public void add(Invoice invoice){
		invoices.add(invoice);
	}
	
	public Invoice get(int index){
		return invoices.get(index);
	}
	
	public abstract void load(int userId);
}
