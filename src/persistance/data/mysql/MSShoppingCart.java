package persistance.data.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistance.MySQLDatabase;
import persistance.data.generic.ShoppingCart;

public class MSShoppingCart extends ShoppingCart {
	
	public MSShoppingCart(){
		super();
	}

	private static final String table = "shoppingcart";

	private static final String index = "id";
	private static final String fkuser = "iduser";
    
	public void load(int userId) { 
		
		String request = "select P.name from "+table+" S, productline SL, product P where S."+fkuser+"="+userId+" and SL.idshoppingcart = S."+index+" and P.id = SL.idproduct";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		
		try {
			while (result.next()){
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public int getIdFromOwner(int userId) {
		String request = "select "+index+" from "+table+" where "+fkuser+" = '"+ userId +"'";
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		int idShoppingCart = 0;
		try {
			while (result.next()){
				idShoppingCart = result.getInt(index);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idShoppingCart;
	}

	@Override
	public void save(int userId) {
		String request = "insert into "+table+"("+fkuser+") values('"+ userId +"')";
		MySQLDatabase.getInstance().insertRequest(request);
	}
}
