package dal.product.generic;


public abstract class Speaker {
	
	private int id;
	private String name;
    private String job;
    private String shortDescription;
    private String longDescription;

    public int getId(){
    	return id;
    }
    
    public void setId(int id){
    	this.id = id;
    }
    
    public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
 	}

	public String getLongDescription() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.longDescription;
    }

    public  void setLongDescription(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.longDescription = value;
    }

    public String getShortDescription() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.shortDescription;
    }

    public void setShortDescription(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.shortDescription = value;
    }

    public String getJob() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.job;
    }

    public  void setJob(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.job = value;
    }

    public abstract String save(String login);
    public abstract String load(int speakerId);
    public abstract void update(int speakerId);
    public abstract int getIdFromSpeaker(int userId);

	public abstract String load(String speakerID);

}
