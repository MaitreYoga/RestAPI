package dal.product.mysql;

import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.OrderLine;
import dal.product.generic.ProductLine;

public class MSOrderLine extends OrderLine {

	private static final String table = "productline";

	/*private static final String index = "id";
	private static final String quantity = "quantity";
	private static final String fkshoppingcart = "idshoppingcart";
	private static final String fkorder = "idorder";
	private static final String fkproduct = "idproduct";*/

    //private List<Order> product = new ArrayList<Order> ();

	@Override
	public void save(List<ProductLine> productsS, int idOrder) {
		for (int i = 0; i<productsS.size(); i++){
			String request = "insert into "+table+" values('"+idOrder+"','"+productsS.get(i).getIdProduct()+"','"+productsS.get(i).getQuantity()+"')";
			MySQLDatabase.getInstance().insertRequest(request);
		}
	}

}
