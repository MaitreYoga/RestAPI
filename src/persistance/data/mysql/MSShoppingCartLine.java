package persistance.data.mysql;

import persistance.MySQLDatabase;
import persistance.data.generic.ShoppingCartLine;

public class MSShoppingCartLine extends ShoppingCartLine {


	private static final String table = "productline";

	private static final String index = "id";
	//private static final String productquantity = "quantity";
	//private static final String fkshoppingcart = "idshoppingcart";
	//private static final String fkorder = "idorder";
	private static final String fkproduct = "idproduct";

	
	@Override
	public void delete(int product, int idShoppingCart) {
		String request = "delete from "+table+" where "+index+" = '"+ idShoppingCart +"' and "+fkproduct+" = '"+ product +"'";
		MySQLDatabase.getInstance().deleteRequest(request);
	}

	@Override
	public void save(int idProd, int idShoppingCart) {
		int quantity = 1;
		String request = "insert into "+table+" values('"+ idShoppingCart +"', '"+ idProd +"', '"+ quantity +"')";
		MySQLDatabase.getInstance().insertRequest(request);
		
	}

}
