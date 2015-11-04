package dal.product.generic;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class Manager 
{
	private int ID;
	private String name;
	
	public Manager() 
	{
		setName("");
		ID=0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public abstract String loadList();

	public abstract boolean save(String user);

	public abstract boolean delete(String userName);

	public abstract List<Object> load();

	public int getID() {
		return ID;
	}
	public void setID(int ID){
		this.ID = ID;
	}

	public abstract void load(String login);

}
