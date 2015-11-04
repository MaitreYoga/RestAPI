package dal.product.mysql;

import java.util.ArrayList;
import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Product;
import dal.product.generic.ProductLine;

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
