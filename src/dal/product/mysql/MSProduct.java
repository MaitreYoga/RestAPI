package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Product;

public class MSProduct extends Product {

	private static final String table = "product";

	private static final String index = "id";
	private static final String name = "name";
	private static final String price = "price";
	private static final String quantityavailable = "quantityavailable";
	private static final String memberreduction = "memberreduction";
	private static final String fkbrand = "idbrand";
	private static final String fkresponsible = "idresponsible";
	private static final String fkcategory = "idcategory";

	public MSProduct() {
		super();
	}
    
	public MSProduct(String name, String brand, int price,
			int quantityAvailable, int memberPrice, int productCat, int seller) {

		super.setName(name);
		super.setBrand(brand);
		super.setPrice(price);
		super.setQuantityAvailable(quantityAvailable);
		super.setMemberPrice(memberPrice);
		super.setProductCat(productCat);
		super.setSeller(seller);
	}
	
	public MSProduct(int idProd, String name, String brand, int price,
			int quantityAvailable, int memberPrice, int productCat, int seller) {
		super();
		super.setIdProd(idProd);
		super.setName(name);
		super.setBrand(brand);
		super.setPrice(price);
		super.setQuantityAvailable(quantityAvailable);
		super.setMemberPrice(memberPrice);
		super.setProductCat(productCat);
		super.setSeller(seller);
	}

	@Override
	public String save() {
		String request = "INSERT INTO "+table+" ("+name+","+fkbrand+","
				+ price+","+quantityavailable+","+memberreduction+","+fkcategory+","+fkresponsible+",urlimage) VALUES ("
				+"'"+getName()+"',"
				+"'"+getBrand()+"',"
				+"'"+getPrice()+"',"
				+"'"+getQuantityAvailable()+"',"
				+"'"+getMemberPrice()+"',"
				+"'"+getProductCat()+"',"
				+"'"+getSeller()+"',"
				+"'"+getUrlImage()+"'"
				+ ")";

		int result = MySQLDatabase.getInstance().insertRequest(request);
		
		if (result!=-1){
			return "ok";
		}
		return "";
		
	}

	@Override
	public void load(int product) {
		String request = "SELECT * FROM "+table+" WHERE "+index+"='"+product+"'";
		
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
    	try {
    		result.beforeFirst();
			while ( result.next() ) {
				super.setIdProd(product);
				super.setName(result.getString(name));
				super.setBrand(result.getString(fkbrand));
				super.setPrice(result.getInt(price));
				super.setQuantityAvailable(result.getInt(quantityavailable));
	    		if (result.getString(memberreduction) != null){
	    			super.setMemberPrice(result.getInt(memberreduction));
	    		} else {
	    			super.setMemberPrice(result.getInt(price));
	    		}
				super.setProductCat(result.getInt(fkcategory));
				super.setSeller(result.getInt(fkresponsible));
				super.setUrlImage(result.getString("urlimage"));
			}
			result.close();
		} catch (SQLException e) {
			// rintStackTrace();
		}
		
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void updateQuantityAvailable(int idProd, int newQuant) {
		String request = "update "+table+" set quantityavailable = '"+ newQuant +"' where "+index+" ='"+ idProd +"'";
		MySQLDatabase.getInstance().updateRequest(request);
	}
}
