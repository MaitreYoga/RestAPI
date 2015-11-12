package bl.manager;

import dal.factory.Factory;
import dal.product.generic.ActivityCategory;
import dal.product.generic.ActivityCategoryList;

public class ActivityCategoryManager 
{
	public Factory factory;
	public ActivityCategory activityCategory;
	
	public ActivityCategoryManager()
	{
		factory = Factory.getInstance();
	}

	public boolean createActivityCategory(String activityCategoryName) 
	{
		activityCategory = factory.makeActivityCategory();
		return activityCategory.save(activityCategoryName);
		
	}

	public String updateCategoryList() 
	{
		activityCategory = factory.makeActivityCategory();
		return activityCategory.loadList();
		
	}

	public boolean deleteActivityCategory(String activityCategoryName) 
	{
		activityCategory = factory.makeActivityCategory();
		return activityCategory.delete(activityCategoryName);
		
	}

	public ActivityCategoryList getAllActivityCategories() {
		ActivityCategoryList catActs = factory.makeActivityCategoryList();
		catActs.load();
		return catActs;
	}

}
