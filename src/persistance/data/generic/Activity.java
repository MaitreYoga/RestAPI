package persistance.data.generic;

public abstract class Activity {

	private String name;
	private String shortDescription;
	private String detailedDescription;
	private Manager manager;
	private String ID;
	private ActivityCategory catAct;
	
	public Activity() 
	{
		ID="";
		name="";
		shortDescription="";
		detailedDescription="";
		manager=null;
		catAct=null;
	}

	public abstract String load(String activityID);

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public ActivityCategory getCatAct() {
		return catAct;
	}
	public void setCatAct(ActivityCategory catAct) {
		this.catAct = catAct;
	}
	
	public String getID() {
		return ""+ID;
	}
	public void setID(String ID){
		this.ID = ID;
	}

	public abstract void save();
	public abstract void update();
	public abstract void updateName();

	public abstract void delete();

	public abstract void updateCatAct();

}
