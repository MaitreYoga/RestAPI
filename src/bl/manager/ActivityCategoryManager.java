package bl.manager;

import dal.factory.Factory;
import dal.product.generic.ActivityCategoryList;

public class ActivityCategoryManager 
{
	public Factory factory;
	
	public ActivityCategoryManager()
	{
		factory = Factory.getInstance();
	}

	public boolean createActivityCategory(String activityCategoryName) 
	{
		return factory.makeActivityCategory().save(activityCategoryName);
	}

	public String updateCategoryList() 
	{
		return factory.makeActivityCategory().loadList();
	}

	public boolean deleteActivityCategory(String activityCategoryName) 
	{
		return factory.makeActivityCategory().delete(activityCategoryName);	
	}

	public ActivityCategoryList getAllActivityCategories() {
		ActivityCategoryList catActs = factory.makeActivityCategoryList();
		catActs.load();
		return catActs;
	}

}
