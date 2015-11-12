package bl.manager;

import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Notification;
import dal.product.generic.NotificationList;

public class NotificationManager {
	
	private Factory factory;
	private NotificationList notifs;

	public NotificationManager(){
		factory = Factory.getInstance();
	}
	
	public String handleActivityCatAsk(int senderId, String activityCategory,List<Integer>receivers) {
		Notification notif = factory.makeNotification();
		notif.setSender(senderId);
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

	public void handleOrderNotif(int senderId, List<Integer> receivers, String message) {
		Notification notif = factory.makeNotification();
		notif.setSender(senderId);
		notif.setMessage(message);
		notif.setReceivers(receivers);
		notif.save();
	}
}