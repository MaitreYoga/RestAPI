package bl.manager;

import dal.factory.Factory;
import dal.product.generic.InvoiceList;

public class InvoicesManager {

	private Factory factory;

    public InvoicesManager(){
    	factory = Factory.getInstance();
    }

	public void createInvoice(int amountPaid, String paymentType, String paymentDate, int idOrder) {
		factory.makeInvoice().save(amountPaid, paymentDate, paymentType, idOrder);
	}

	public InvoiceList getAllInvoices(int userId) {
		InvoiceList invoiceL = factory.makeInvoiceList();
		invoiceL.load(userId);
		return invoiceL;
	}
}