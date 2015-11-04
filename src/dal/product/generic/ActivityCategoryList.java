package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityCategoryList {
	private List<ActivityCategory> catActs;
	
	public ActivityCategoryList(){
		catActs = new ArrayList<ActivityCategory>();
	}
	
	public int size() {
		return catActs.size();
	}

	public ActivityCategory get(int i) {
		return catActs.get(i);
	}

	public ActivityCategory get(String string) {
		int i = 0;
		while(i < catActs.size() && !catActs.get(i).getName().equals(string))
			i++;
		return catActs.get(i);
	}
	
	public void add(ActivityCategory access){
		catActs.add(access);
	}
	public void remove(ActivityCategory access){
		catActs.remove(access);
	}
	public void remove(int index){
		catActs.remove(index);
	}

	public abstract void load();
}
