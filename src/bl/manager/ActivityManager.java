package bl.manager;

import java.util.ArrayList;
import java.util.List;

import persistance.data.generic.Accessory;
import persistance.data.generic.Activity;
import persistance.data.generic.ActivityList;
import persistance.factory.Factory;

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