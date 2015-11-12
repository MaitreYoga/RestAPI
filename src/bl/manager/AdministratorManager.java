package bl.manager;

import java.util.ArrayList;
import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Administrator;
import dal.product.generic.AdministratorList;

public class AdministratorManager {
	private Factory factory;
	private AdministratorList admins;

	public AdministratorManager(){
		factory = Factory.getInstance();
	}

	public List<Integer> getAllAdministratorID() {
		admins = factory.makeAdministratorList();
		admins.load();
		List<Integer> logins = new ArrayList<Integer>();
		for(int i = 0 ; i < admins.size() ; i++)
			logins.add(new Integer(admins.get(i).getID()));
		return logins;
	}
	
	public int getAdministratorID(String login){
		Administrator a = factory.makeAdministrator();
		a.load(login);
		return a.getID();
	}
}
