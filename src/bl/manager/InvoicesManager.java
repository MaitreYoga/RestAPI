package bl.manager;

import dal.factory.Factory;
import dal.product.generic.Invoice;
import dal.product.generic.InvoiceList;

public class InvoicesManager {

	private Factory factory;
	private Invoice invoice;
	private InvoiceList invoiceL;

    public InvoicesManager(){
    	factory = Factory.getInstance();
    }

	public void createInvoice(int amountPaid, String paymentType, String paymentDate, int idOrder) {
		invoice = factory.makeInvoice();
		invoice.save(amountPaid, paymentDate, paymentType, idOrder);
	}

	public InvoiceList getAllInvoices(int userId) {
		invoiceL = factory.makeInvoiceList();
		invoiceL.load(userId);
		return invoiceL;
	}
}