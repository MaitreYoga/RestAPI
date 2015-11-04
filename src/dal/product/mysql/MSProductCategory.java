package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.ProductCategory;

public class MSProductCategory extends ProductCategory {

	private static final String table = "category";

	private static final String index = "id";
	private static final String name = "name";
	private static final String fkparent = "idparentcategory";


    public MSProductCategory(){
    	super();
    }
    
    public MSProductCategory(int id, String lib){
    	super(id,lib);
    }
    
    public MSProductCategory(int id, String lib, int parent){
    	super(id,lib,parent);
    }

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update() {
		String request = "UPDATE "+table+" SET "+name+" ='"+getLibCat()+"' WHERE "+index+"='"+getIdCategory()+"'";
    	return MySQLDatabase.getInstance().updateRequest(request);
		
		
	}

	@Override
	public void loadPrimaryCategoryList() {
		String request = "SELECT * FROM "+table+" where "+fkparent+" IS NULL";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try
		{
			while ( result.next() ) {
	    		this.setIdCategory(0);
	    		this.setLibCat("root");
	    		this.subCategoryList.add(new MSProductCategory(result.getInt(index),result.getString(name)));
			}
	    	/* On ferme le ResultSet */
		    result.close();

		}
		catch (SQLException e){}
		catch (NullPointerException e){}
		
	}


	@Override
	public void loadSubCategoryList(int idParentCat) {
		String request = "SELECT * FROM "+table+" where "+fkparent+"='"+idParentCat+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try
		{
	    	while ( result.next() ) {
	    		this.setIdCategory(idParentCat);
	    		this.subCategoryList.add(new MSProductCategory(result.getInt(index),result.getString(name),result.getInt(fkparent)));
			}
	    	/* On ferme le ResultSet */
		    result.close();
		}
		catch (SQLException e){}
		catch (NullPointerException e){}
		
	}

	@Override
	public void loadProductList(int parentCat) {
		String request = "SELECT * FROM product where idcategory='"+parentCat+"'";
    	ResultSet result = MySQLDatabase.getInstance().selectRequest(request);
		try
		{
			while ( result.next() ) {
				this.setIdCategory(parentCat);
	    		this.productList.add(new MSProduct(result.getInt("id"),result.getString("name"),
	    				result.getString("idbrand"),result.getInt("price"),result.getInt("quantityavailable"),
	    				result.getInt("memberreduction"),result.getInt("idcat"),result.getInt("idseller")));
			}
	    	/* On ferme le ResultSet */
		    result.close();

		}
		catch (SQLException e){}
		catch (NullPointerException e){}
		
	}

	@Override
	public String save() {
		String request;
		if (getParentCat() == 0){
			request = "INSERT INTO "+table+" ("+name+") VALUES ("
					+"'"+getLibCat()+"'"
					+ ")";
		}else{
			request = "INSERT INTO "+table+" ("+name+","+fkparent+") VALUES ("
					+"'"+getLibCat()+"',"
					+"'"+getParentCat()+"'"
					+ ")";
		}
		int result = MySQLDatabase.getInstance().insertRequest(request);
		
		if (result!=-1){
			return "ok";
		}
		return "";
	}

	@Override
	public int delete(int catId) {
		String request = "DELETE FROM "+table+" WHERE "+index+"='"+getIdCategory()+"'";
		return MySQLDatabase.getInstance().deleteRequest(request);
	}
}
