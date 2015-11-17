package dal.product.generic;

public abstract class Event
{
	private int id;
    private String name;
    private int price;
    private String place ;
    private String period ;
    private String startDate ;
    private String endDate ;
    private String activity ;
    private String speaker;
    private String room;
    private String state;
    
    public Event()
    {

    }
    
	public abstract String save();
	public abstract Event load(int eventId);
    
    public int getId()
    {
    	return id;
    }
    
    public void setId(int id){
    	this.id = id;
    }
     
	public String getName() 
	{
		return name;
	}

	public void setName(String value) 
	{
		this.name = value;
	}
	
	public String getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(String value) 
	{
		this.startDate = value;
	}
	public String getEndDate() 
	{
		return endDate;
	}

	public void setEndDate(String value) 
	{
		this.endDate = value;
	}
	public String getState() 
	{
		return state;
	}

	public void setState(String value) 
	{
		this.state = value;
	}
	public String getRoom() 
	{
		return room;
	}

	public void setRoom(String value) 
	{
		this.room = value;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int value)
	{
		this.price = value;
	}

	public String getActivity()
	{
		return activity;
	}

	public void setActivity(String value) 
	{
		this.activity = value;
	}

	public String getPlace() 
	{
		return place;
	}

	public void setPlace(String value) 
	{
		this.place = value;
	}
	
	public String getPeriod() 
	{
		return period;
	}

	public void setPeriod(String value) 
	{
		this.period = value;
	}

	public String getSpeaker() 
	{
		return speaker;
	}

	public void setSpeaker(String value) 
	{
		this.speaker = value;
	}

}
