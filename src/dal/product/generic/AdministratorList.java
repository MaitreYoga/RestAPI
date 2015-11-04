package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class AdministratorList{

	private List<Administrator> admins;
	
	public AdministratorList(){
		admins = new ArrayList<Administrator>();
	}
	
	public abstract String load();
	
	public void add(Administrator admin){
		admins.add(admin);
	}
	public Administrator get(int index){
		return admins.get(index);
	}
	public int size(){
		return admins.size();
	}

}
