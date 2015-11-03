package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class ManagerList {

	private List<Manager> managers;
	
	public ManagerList(){
		managers = new ArrayList<Manager>();
	}
	
	public int size(){
		return managers.size();
	}
	public void add(Manager manager){
		managers.add(manager);
	}
	public Manager get(int index){
		return managers.get(index);
	}
	public Manager get(String name){
		int i = 0;
		while(i < managers.size() && !managers.get(i).getName().equals(name))
			i++;
		return managers.get(i);
	}

	public abstract void load();

}
