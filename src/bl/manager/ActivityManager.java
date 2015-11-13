package bl.manager;

import dal.factory.Factory;
import dal.product.generic.Activity;
import dal.product.generic.ActivityList;

public class ActivityManager {

	private Factory factory;
	
	public ActivityManager(){
		factory = Factory.getInstance();
	}
	public ActivityList getAllActivities() {
		ActivityList activities = factory.makeActivityList();
		activities.load();
		return activities;
	}
	public Activity addActivity(String name) {
		Activity act = factory.makeActivity();
		act.setName(name);
		act.save();
		return act;
	}
}