package bl.manager;

import javax.swing.JTextArea;

import persistance.data.generic.Manager;
import persistance.data.generic.ManagerList;
import persistance.factory.Factory;

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
