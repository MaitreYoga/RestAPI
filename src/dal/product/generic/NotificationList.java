package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class NotificationList {

	private List<Notification> notifs;
	
	public NotificationList(){
		notifs = new ArrayList<Notification>();
	}
	
	public int size(){
		return notifs.size();
	}
	
	public void add(Notification notif){
		notifs.add(notif);
	}
	
	public Notification get(int index){
		return notifs.get(index);
	}
	
	public abstract void load(int idReceiver);
}
