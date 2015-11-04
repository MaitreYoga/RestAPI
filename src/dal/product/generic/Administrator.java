package dal.product.generic;

public abstract class Administrator {

	private int ID;
	private String user;

	public void setUser(String userAdmin) {
		user = userAdmin;
	}
	
	public String getUser() {
		return user;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public abstract void load(String login);
}