package bl.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import persistance.data.generic.ActivityCategory;
import persistance.data.generic.Manager;
import persistance.data.generic.Member;
import persistance.data.generic.ProductList;
import persistance.data.generic.Speaker;
import persistance.data.generic.SubscriptionPayment;
import persistance.data.generic.SubscriptionPaymentList;
import persistance.data.generic.User;
import persistance.factory.Factory;
import persistance.Session;

public class UserManager {
	
	private Factory factory;
	private User user;
	private Member member;
	private Manager manager;
	private SubscriptionPayment subscriptionPayment;
	private SubscriptionPaymentList subscriptionPaymentL;
	private ActivityCategory activityCategory;
    
    public UserManager(){
    	factory = Factory.getInstance();
    }

    public String handleLogin(String login, String pwd) {
    	user = factory.makeUser();
    	String loadResult = user.load(login, pwd);
    	if(loadResult == null) {
    		Session.setUserForSession(user);
    	}
    	return loadResult;
    }
	public String handleSubscribe(String login, String pwd, String firstName, String lastName, String phone, String mail, String adressNumber, String adressName, String postalCode, String town) {
		user = factory.makeUser();
		
		String resultMessage = user.check(login);
		if(resultMessage.equals("User registered"))
			return "Login already exists - Please try another one";
		if(resultMessage.equals("User not registered")){
			user.setLogin(login);
			user.setPwd(pwd);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setMail(mail);
			//user.setNumber(adressNumber);
			//user.setName(adressName);
			//user.setPostalCode(postalCode);
			//user.setTown(town);
			
			return user.save();
		}
		else
			return "An error has occured. Please retry";
	}

	public String getLoginFromMember(int seller) {
		member = factory.makeMember();
		return member.getLogin(seller);
	}
	
	public int getSellerFromLogin(int userId) {
		member = factory.makeMember();
		return member.getSeller(userId);
	}
	
	public int getIdFromLogin(String login) {
		user = factory.makeUser();
		return user.loadId(login);
	}

	public List<Object> getUsers() 
	{
		user = factory.makeUser();
		return user.load();
	}

	public boolean handleManagerAdd(String user)
	{
		manager = factory.makeManager();
		return manager.save(user);
	}

	public boolean handleManagerDelete(String userName)
	{
		manager = factory.makeManager();
		return manager.delete(userName);
		
	}

	public List<String> getLoginsFromMembers(List<Integer> sellers) {
		List<String> sellersLogins = new ArrayList<String>();
		boolean test;
		for (int i = 0; i<sellers.size();i++){
			test=false;
			for (int j = 0; j<sellersLogins.size();j++){
				if (getLoginFromMember(sellers.get(i)) == sellersLogins.get(j)){
					test=true;
				}
			}
			if (!test){
				sellersLogins.add(getLoginFromMember(sellers.get(i)));
			}
		}
		return sellersLogins;
	}
	
	public int getMemberID(String login){
		Member m = factory.makeMember();
		return m.getMemberId(login);
	}
	public String getSpeakerID(String userID){
		Speaker s = factory.makeSpeaker();
		int id = s.getIdFromSpeaker(userID);
		if(id == 0)
			return null;
		return ""+id;
	}
	
	public List<Object> getManagers() 
	{
		manager = factory.makeManager();
		return manager.load();
		
	}

	public List<Object> getCategories() 
	{
		activityCategory = factory.makeActivityCategory();
		return activityCategory.load();
		
	}

	public int getUserID() {
		return Session.user().getId();
	}
	
	public String getUserLogin() {
		return Session.user().getLogin();
	}

	public SubscriptionPaymentList getPaymentsFromMember(int idMember) {
		subscriptionPaymentL = factory.makeSubscriptionPaymentList();
		subscriptionPaymentL.load(idMember);
		return subscriptionPaymentL;
	}

	public SubscriptionPayment getLastSubscriptionPayment(int idMember) {
		subscriptionPayment = factory.makeSubscriptionPayment(); 
		return subscriptionPayment.getLastSubscriptionPayment(idMember);
	}

	public int registerMember(int userId) {
		member = factory.makeMember();
		
		// on recupere la date du jour
		String registrationDate;
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		Date date = new Date();
		registrationDate = formater.format( date );
		
		return member.save(userId, registrationDate);
		
	}

	public void createSubscriptionPayment(int idMember, int amountPaid, String paymentType) {
		subscriptionPayment = factory.makeSubscriptionPayment();
		
		// on recupere la date du jour
		String paymentDate;
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		Date date = new Date();
		paymentDate = formater.format( date );
		
		subscriptionPayment.save(amountPaid, paymentDate, paymentType, idMember);
		
	}
	
}
