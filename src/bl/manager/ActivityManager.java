package bl.manager;

import java.util.ArrayList;
import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Activity;
import dal.product.generic.ActivityList;

public class ActivityManager {

	private Factory factory;
	
	private List<Activity> activities;
	
	public ActivityManager(){
		factory = Factory.getInstance();
		activities = new ArrayList<Activity>();
	}
	public ActivityList getAllActivities() {
		ActivityList activities = factory.makeActivityList();
		activities.load();
		return activities;
	}
	public Activity addActivity(String name) {
		Activity act = factory.makeActivity();
		act.setName(name);
		activities.add(act);
		act.save();
		return act;
	}
}