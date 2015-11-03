package persistance.data.generic;


public abstract class Inscription
{
    private String state;

    String getState() 
    {
        return this.state;
    }

    void setState(String value) 
    {
        this.state = value;
    }

	public abstract void save(int userID, String chosenEvent);

	public abstract boolean save(int userID, int chosenEvent, int amount);

}
