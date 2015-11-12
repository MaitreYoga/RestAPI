package dal.factory;

import dal.product.generic.*;
import dal.product.mysql.*;

public class MySQLFactory extends Factory
{
	protected MySQLFactory(){
		
	}
	
	@Override
	public User makeUser() {
    	return new MSUser();
	}

	@Override
	public Inscription makeInscription() {
		return new MSInscription();
	}

	@Override
	public Event makeEvent() {
		return new MSEvent();
	}

	@Override
	public EventList makeEventList() {
		return new MSEventList();
	}

	@Override
	public Speaker makeSpeaker() {
		return new MSSpeaker();
	}	

	@Override
	public Notification makeNotification() {
		return new MSNotification();
	}

	@Override
	public AdministratorList makeAdministratorList() {
		return new MSAdministratorList();
	}

	@Override
	public Administrator makeAdministrator() {
		return new MSAdministrator();
	}
	
	@Override
	public ProductCategory makeCategory() {
		return new MSProductCategory();
	}

	@Override
	public ShoppingCart makeShoppingCart() {
    	return new MSShoppingCart();
	}

	@Override
	public NotificationList makeNotificationList() {
		return new MSNotificationList();
	}

	@Override
	public ProductLineList makeProductLineList() {
		return new MSProductLineList();
	}

	@Override
	public Product makeProduct() {
		return new MSProduct();
	}
	
	public Product makeProduct(int idProd, String name, String brand, int price,int quantityAvailable, int memberPrice, int productCat, int seller){
		return new MSProduct(idProd,name,brand,price,quantityAvailable,memberPrice,productCat,seller);
	}

	@Override
	public Period makePeriod() {
		return new MSPeriod();
	}

	@Override
	public Activity makeActivity() {
		return new MSActivity();
	}

	@Override
	public Room makeRoom() {
		return new MSRoom();
	}

	@Override
	public Member makeMember() {
		return new MSMember();
	}

	@Override
	public ActivityCategory makeActivityCategory() {
		return new MSActivityCategory();
	}

	@Override
	public Manager makeManager() {
		return new MSManager();
	}

	@Override
	public SpeakerList makeSpeakerList() {
		return new MSSpeakerList();
	}

	@Override
	public ActivityList makeActivityList() {
		return new MSActivityList();
	}

	@Override
	public Order makeOrder() {
		return new MSOrder();
	}

	@Override
	public ManagerList makeManagerList() {
		return new MSManagerList();
	}

	@Override
	public OrderLine makeOrderLine() {
		return new MSOrderLine();
	}

	@Override
	public OrderLineList makeOrderLineList() {
		return new MSOrderLineList();
	}
	
	@Override
	public OrderList makeOrderList() {
		return new MSOrderList();
	}

	@Override
	public AccessoryList makeAccessoryList() {
		return new MSAccessoryList();
	}

	@Override
	public Accessory makeAccessory() {
		return new MSAccessory();
	}

	@Override
	public ShoppingCartLine makeShoppingCartLine() {
		return new MSShoppingCartLine();
	}

	@Override
	public Invoice makeInvoice() {
		return new MSInvoice();
	}

	@Override
	public InvoiceList makeInvoiceList() {
		return new MSInvoiceList();
	}

	@Override
	public ActivityCategoryList makeActivityCategoryList() {
		return new MSActivityCategoryList();
	}

	@Override
	public SubscriptionPayment makeSubscriptionPayment() {
		return new MSSubscriptionPayment();
	}

	@Override
	public SubscriptionPaymentList makeSubscriptionPaymentList() {
		return new MSSubscriptionPaymentList();
	}

	@Override
	public ProductList makeProductList() {
		return new MSProductList();
	}

	@Override
	public RoomList makeRoomList() {
		return new MSRoomList();
	}

	@Override
	public Address makeAddress() {
		return new MSAddress();
	}
}