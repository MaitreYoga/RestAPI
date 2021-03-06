package dal.product.generic;

import java.util.List;

public abstract class ActivityCategory 
{
	private String ID;
	private String name;
	
	public ActivityCategory() {
		setID("");
		setName("");
	}

	public abstract boolean save(String activityCategoryName);

	public abstract List<Object> load();

	public abstract boolean delete(String activityCategoryName);

	public abstract String loadList();

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
