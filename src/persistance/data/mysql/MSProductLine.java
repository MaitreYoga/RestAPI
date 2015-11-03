package persistance.data.mysql;

import java.util.ArrayList;
import java.util.List;

import persistance.MySQLDatabase;
import persistance.data.generic.Product;
import persistance.data.generic.ProductLine;

public class MSProductLine extends ProductLine {

	private static final String table = "productline";

	private static final String index = "id";
	private static final String productquantity = "quantity";
	private static final String fkshoppingcart = "idshoppingcart";
	private static final String fkorder = "idorder";
	private static final String fkproduct = "idproduct";
	
    private int quantity;
    private List<Product> product = new ArrayList<Product> ();
    private MySQLDatabase mySQLDatabase;

}
