package bl.manager;

import java.util.ArrayList;
import java.util.List;

import persistance.data.generic.Administrator;
import persistance.data.generic.AdministratorList;
import persistance.factory.Factory;

public class AdministratorManager {
	private Factory factory;
	private AdministratorList admins;

	public AdministratorManager(){
		factory = Factory.getInstance();
	}

	public List<String> getAllAdministratorID() {
		admins = factory.makeAdministratorList();
		admins.load();
		List<String> logins = new ArrayList<String>();
		for(int i = 0 ; i < admins.size() ; i++)
			logins.add(admins.get(i).getUser());
		return logins;
	}
	
	public int getAdministratorID(String login){
		Administrator a = factory.makeAdministrator();
		a.load(login);
		return a.getID();
	}
}
