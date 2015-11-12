package dal.product.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.MySQLDatabase;
import dal.product.generic.Address;

public class MSAddress extends Address {

	@Override
	public void save() {
		String request = "insert into adress (number, name, postalcode, town)"
						+ "values ("	+super.getNumber()+","
										+"'"+super.getName()+"',"
										+super.getPostalCode()+","
										+"'"+super.getTown()+"');";
		MySQLDatabase.getInstance().insertRequest(request);
	}

	@Override
	public void load(int idAdress) {
		String request = "select * from adress where id = "+idAdress;
		ResultSet result = MySQLDatabase.getInstance().selectRequest(request);	
		this.resultToAdress(result);
	}

	@Override
	public void update() {
		String request = "update adress set "
						+"number = "+super.getNumber()
						+", name = '"+super.getName()
						+"', postalcode = "+super.getPostalCode()
						+", town = '"+super.getTown()
						+"' where id = "+super.getId();
		MySQLDatabase.getInstance().updateRequest(request);
	}

	private void resultToAdress (ResultSet result)
	{
		try
		{
			if(result.next())
			{
				super.setId(result.getInt("id"));
				super.setNumber(result.getInt("number"));
				super.setName(result.getString("name"));
				super.setPostalCode(result.getInt("postalcode"));
				super.setTown(result.getString("town"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
	}
}