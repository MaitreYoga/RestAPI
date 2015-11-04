package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityList {

	private List<Activity> activities;
	
	public ActivityList(){
		activities = new ArrayList<Activity>();
	}
	
	public int size(){
		return activities.size();
	}
	public Activity get(int index){
		return activities.get(index);
	}
	public void add(Activity activity){
		activities.add(activity);
	}
	public void remove(Activity activity){
		activities.remove(activity);
	}
	public void remove(int index){
		activities.remove(index);
	}

	public abstract void load();

}
