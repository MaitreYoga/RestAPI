package bl.facade;

import java.util.List;

import bl.manager.AdministratorManager;
import bl.manager.ManagerManager;
import bl.manager.NotificationManager;
import bl.manager.ProductManager;
import bl.manager.ShoppingCartManager;
import bl.manager.SpeakerManager;
import bl.manager.UserManager;
import dal.product.generic.NotificationList;
import dal.product.generic.ProductList;
import dal.product.generic.Speaker;
import dal.product.generic.SubscriptionPayment;
import dal.product.generic.SubscriptionPaymentList;
import dal.product.generic.User;

public class UserFacade {

	private UserManager userManager;
	private SpeakerManager speakerManager;
	private AdministratorManager administratorManager;
	private ManagerManager managerManagementManager;
	private ShoppingCartManager cartManager;
	private ProductManager productManager;
	
	private NotificationManager notificationManager;
	
	private static UserFacade instance;
	
	public static UserFacade instance() {
		if(instance == null)
			instance = new UserFacade();
		return instance;
	}
    
    private UserFacade(){
    	userManager = new UserManager();
    	speakerManager = new SpeakerManager();
    	notificationManager = new NotificationManager();
    	administratorManager = new AdministratorManager();
    	managerManagementManager = new ManagerManager();
    	productManager = new ProductManager();
    	cartManager = new ShoppingCartManager();
    }
    
    public User handleLogin(String login, String pwd) {
    	//Set the user
    	return userManager.handleLogin(login, pwd);
    	/*if(token!=null){
    		Session.setSpeakerID(speakerManager.getIdFromSpeaker(login));
    		Session.setManagerID(managerManagementManager.getManagerID(login));
    		Session.setAdminID(administratorManager.getAdministratorID(login));
    		Session.setMemberID(userManager.getMemberID(login));
    	}
    	return token;*/
    }

	public String handleSubscribe(String login, String pwd, String firstName, String lastName, String phone, String mail, String number, String name, String postalCode, String town, boolean isSpeaker, String job) {
		String errorMessage = userManager.handleSubscribe(login, pwd, firstName, lastName, phone, mail, number, name, postalCode, town);
		if(errorMessage == null && isSpeaker) {
			errorMessage = speakerManager.handleSubscribe(login,job);
		}
		return errorMessage;
	}

	public String handleActivityCatAsk(int userId, String activityCategory) {
		List<Integer> administratorIds = administratorManager.getAllAdministratorID();
		return notificationManager.handleActivityCatAsk(userId,activityCategory,administratorIds);
	}

	public NotificationList getNotifications(int userId) {
		return notificationManager.getNotificationsForLogin(userId);
	}

	public void setNotificationViewed(int numNotif) {
		notificationManager.setNotificationViewed(numNotif);
	}

	public String getLoginFromMember(int idMember) {
		return userManager.getLoginFromMember(idMember);
	}
	
	public int getIdFromLogin(String login){
		return userManager.getIdFromLogin(login);
	}

	public Speaker getSpeaker(int userId) {
		int speakerId = speakerManager.getIdFromSpeaker(userId);
		return speakerManager.getSpeaker(speakerId);
	}

	public void editSpeaker(int speakerId, String job, String sDescritpion, String lDescription) {
		speakerManager.handleEdit(speakerId, job, sDescritpion, lDescription);		
	}

	public String updateManagerList() 
	{
		return managerManagementManager.updateManagerList();
	}

	public List<User> getUsers() 
	{
		return userManager.getUsers();
	}
	
	public User getUser(int id)
	{
		return userManager.getUser(id,"");
	}
	public User getUser(int id, String login)
	{
		return userManager.getUser(id,login);
	}
	
	public boolean handleManagerAdd(String user)
	{
		return userManager.handleManagerAdd(user);
		
	}

	public void sendNotifOrder(int senderId, List<Integer> receivers, String message) {
		notificationManager.handleOrderNotif(senderId, receivers, message);
	}

	public boolean handleManagerDelete(String userName) 
	{
		return userManager.handleManagerDelete(userName);
		
	}

	public List<Object> getManagers() 
	{
		return userManager.getManagers();
		
	}

	public void createShoppingCart(int userId) {
		cartManager.createShoppingCart(userId);
	}

	public int getMemberFromLogin(int userId) {
		return userManager.getMemberID(userId);
	}

	public SubscriptionPaymentList getPaymentsFromMember(int idMember) {
		return userManager.getPaymentsFromMember(idMember);
	}

	public ProductList getProductsFromMember(int idMember) {
		return productManager.getProductsFromMember(idMember);
	}

	public SubscriptionPayment getLastSubscriptionPayment(int idMember) {
		return userManager.getLastSubscriptionPayment(idMember);
	}

	public int registerMember(int userId) {
		return userManager.registerMember(userId);
	}

	public void createSubscriptionPayment(int idMember, int amountPaid, String paymentType) {
		userManager.createSubscriptionPayment(idMember, amountPaid, paymentType);
		
	}

	public boolean verifyToken(String login, String token) {
		return userManager.verifyToken(login, token);
	}

}