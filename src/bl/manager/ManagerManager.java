package bl.manager;

import javax.swing.JTextArea;

import dal.factory.Factory;
import dal.product.generic.Manager;
import dal.product.generic.ManagerList;

public class ManagerManager 
{
	public Factory factory;
	public Manager managerManagement;

	public ManagerManager() 
	{
		factory = Factory.getInstance();
	}


	public String updateManagerList()
	{
		managerManagement = factory.makeManager();
		return managerManagement.loadList();
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
