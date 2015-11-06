package bl.facade;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bl.manager.AdministratorManager;
import bl.manager.ManagerManager;
import bl.manager.NotificationManager;
import bl.manager.ProductManager;
import bl.manager.ShoppingCartManager;
import bl.manager.SpeakerManager;
import bl.manager.UserManager;
import dal.Session;
import dal.product.generic.Notification;
import dal.product.generic.NotificationList;
import dal.product.generic.Product;
import dal.product.generic.ProductLineList;
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
    
    public UserFacade(){
    	userManager = new UserManager();
    	speakerManager = new SpeakerManager();
    	notificationManager = new NotificationManager();
    	administratorManager = new AdministratorManager();
    	managerManagementManager = new ManagerManager();
    	productManager = new ProductManager();
    	cartManager = new ShoppingCartManager();
    }
    
    public String handleLogin(String login, String pwd) {
    	//Set the user
    	String errorMessage = userManager.handleLogin(login, pwd);
    	if(errorMessage==null){
    		Session.setSpeakerID(speakerManager.getIdFromSpeaker(login));
    		Session.setManagerID(managerManagementManager.getManagerID(login));
    		Session.setAdminID(administratorManager.getAdministratorID(login));
    		Session.setMemberID(userManager.getMemberID(login));
    	}
    	return errorMessage;
    }

	public String handleSubscribe(String login, String pwd, String firstName, String lastName, String phone, String mail, String adressNumber, String adressName, String postalCode, String town, boolean isSpeaker, String job) {
		String errorMessage = userManager.handleSubscribe(login, pwd, firstName, lastName, phone, mail, adressNumber, adressName, postalCode, town);
		if(errorMessage == null && isSpeaker) {
			errorMessage = speakerManager.handleSubscribe(login,job);
		}
		return errorMessage;
	}

	public String handleActivityCatAsk(String activityCategory) {
		String userLogin = Session.user().getLogin();
		List<String> administratorLogins = administratorManager.getAllAdministratorID();
		return notificationManager.handleActivityCatAsk(userLogin,activityCategory,administratorLogins);
	}

	public NotificationList getNotifications() {
		int userId = Session.user().getId();
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

	public Speaker getSpeaker() {
		String login = Session.user().getLogin();
		int speakerId = speakerManager.getIdFromSpeaker(login);
		return speakerManager.getSpeaker(speakerId);
	}

	public void editSpeaker(String job, String sDescritpion, String lDescription) {
		String login = Session.user().getLogin();
		int speakerId = speakerManager.getIdFromSpeaker(login);
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

	public void sendNotifOrder(List<String> receivers, String message) {
		String senderLogin = Session.user().getLogin();
		notificationManager.handleOrderNotif(senderLogin, receivers, message);
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

	public int getMemberFromLogin() {
		String login = Session.user().getLogin();
		return userManager.getMemberID(login);
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

	public boolean isMember() {
		/*String login = Session.user().getLogin();
		System.out.println("2"+ userManager.getMemberID(login));
		return !(userManager.getMemberID(login) == null);*/
		return Session.checkPermission("Member");
	}

	public int registerMember() {
		int userId = Session.user().getId();
		return userManager.registerMember(userId);
	}

	public void createSubscriptionPayment(int idMember, int amountPaid, String paymentType) {
		userManager.createSubscriptionPayment(idMember, amountPaid, paymentType);
		
	}

}