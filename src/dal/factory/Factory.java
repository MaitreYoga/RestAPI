package dal.factory;

import dal.product.generic.*;

public abstract class Factory
{
	private static Factory factory;
	
	public static Factory getInstance(){
		if(factory == null)
			factory = new MySQLFactory();
		return factory;
	}
	
    public abstract User makeUser();

	public abstract Inscription makeInscription();

	public abstract Event makeEvent();

	public abstract EventList makeEventList();

	public abstract Speaker makeSpeaker();

	public abstract Notification makeNotification();

	public abstract AdministratorList makeAdministratorList();

	public abstract Administrator makeAdministrator();

	public abstract ProductCategory makeCategory();

	public abstract NotificationList makeNotificationList();

	public abstract ShoppingCart makeShoppingCart();
	
	public abstract ProductLineList makeProductLineList();
	
	public abstract Product makeProduct();
	
	public abstract Period makePeriod();

	public abstract Activity makeActivity();

	public abstract Room makeRoom();

	public abstract Member makeMember();

	public abstract ActivityCategory makeActivityCategory();

	public abstract Manager makeManager();

	public abstract SpeakerList makeSpeakerList();

	public abstract ActivityList makeActivityList();
	
	public abstract Order makeOrder();
	
	public abstract OrderLine makeOrderLine();
	
	public abstract OrderLineList makeOrderLineList();

	public abstract ManagerList makeManagerList();

	public abstract AccessoryList makeAccessoryList();
	
	public abstract Accessory makeAccessory();

	public abstract OrderList makeOrderList();
	
	public abstract ShoppingCartLine makeShoppingCartLine();
	
	public abstract Invoice makeInvoice();
	
	public abstract InvoiceList makeInvoiceList();

	public abstract ActivityCategoryList makeActivityCategoryList();
	
	public abstract SubscriptionPayment makeSubscriptionPayment();
	
	public abstract SubscriptionPaymentList makeSubscriptionPaymentList();
	
	public abstract ProductList makeProductList();

	public abstract RoomList makeRoomList();
	
	public abstract Address makeAddress();
}