package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class Notification {
	
	private int id;
	private boolean isRead;
	private String message;
	private int sender;
	private List<Integer> receivers;
	
	public Notification(){
		id = 0;
		isRead = false;
		message = "";
		sender = 0;
		receivers = new ArrayList<Integer>();
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
	public void setSender(int userId) {
		sender = userId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReceivers(List<Integer> receivers) {
		this.receivers = receivers;
	}
	
	public void addReceiver(int userId){
		receivers.add(new Integer(userId));
	}
	
	public void setRead(boolean read){
		isRead = read;
	}
	
	public int getSender(){
		return sender;
	}
	
	public List<Integer> getReceivers(){
		return receivers;
	}
	
	public String getMessage(){
		return message;
	}
	
	public boolean getRead(){
		return isRead;
	}

	public abstract void save();
	public abstract void read(int idNotif);
}