package bl.manager;

import java.util.List;

import persistance.data.generic.Notification;
import persistance.data.generic.NotificationList;
import persistance.factory.Factory;

public class NotificationManager {
	
	private Factory factory;
	private NotificationList notifs;

	public NotificationManager(){
		factory = Factory.getInstance();
	}
	
	public String handleActivityCatAsk(String userLogin, String activityCategory,List<String>receivers) {
		Notification notif = factory.makeNotification();
		notif.setSender(userLogin);
		notif.setMessage(activityCategory);
		notif.setReceivers(receivers);
		notif.save();

		return null;
	}

	public NotificationList getNotificationsForLogin(int userId) {
		notifs = factory.makeNotificationList();
		notifs.load(userId);
		return notifs;
	}

	public void setNotificationViewed(int numNotif) {
		Notification notif = factory.makeNotification();
		notif.read(numNotif);
	}

	public void handleOrderNotif(String sender, List<String> receivers, String message) {
		Notification notif = factory.makeNotification();
		notif.setSender(sender);
		notif.setMessage(message);
		notif.setReceivers(receivers);
		notif.save();
	}
}