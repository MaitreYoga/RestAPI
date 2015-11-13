package bl.manager;

import dal.factory.Factory;
import dal.product.generic.Manager;
import dal.product.generic.ManagerList;

public class ManagerManager 
{
	public Factory factory;

	public ManagerManager() 
	{
		factory = Factory.getInstance();
	}


	public String updateManagerList()
	{
		return factory.makeManager().loadList();
	}

	public ManagerList getAllManagers() {
		ManagerList managers = factory.makeManagerList();
		managers.load();
		return managers;
	}
	
	public int getManagerID(String login){
		Manager m = factory.makeManager();
		m.load(login);
		return m.getID();
	}
}
