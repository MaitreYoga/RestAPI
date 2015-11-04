package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class Notification {
	
	private int id;
	private boolean isRead;
	private String message;
	private String sender;
	private List<String> receivers;
	
	public Notification(){
		id = 0;
		isRead = false;
		message = "";
		sender = "";
		receivers = new ArrayList<String>();
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
	public void setSender(String userSender) {
		sender = userSender;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	
	public void addReceiver(String receiver){
		receivers.add(receiver);
	}
	
	public void setRead(boolean read){
		isRead = read;
	}
	
	public String getSender(){
		return sender;
	}
	
	public List<String> getReceivers(){
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