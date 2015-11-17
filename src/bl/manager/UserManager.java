package bl.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Address;
import dal.product.generic.Member;
import dal.product.generic.Speaker;
import dal.product.generic.SubscriptionPayment;
import dal.product.generic.SubscriptionPaymentList;
import dal.product.generic.User;

public class UserManager {
	
	private Factory factory;
    
    public UserManager(){
    	factory = Factory.getInstance();
    }

    public User handleLogin(String login, String pwd) {
    	return factory.makeUser().load(login, pwd);
    }
	public String handleSubscribe(String login, String pwd, String firstName, String lastName, String phone, String mail, String number, String name, String postalCode, String town) {
		User u = factory.makeUser();
		Address a = factory.makeAddress();
		
		String resultMessage = u.check(login);
		if(resultMessage.equals("User registered"))
			return "Login already exists - Please try another one";
		if(resultMessage.equals("User not registered")){
			u.setLogin(login);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setPhone(phone);
			u.setMail(mail);
			u.save(pwd);
			
			if(	number != null &&
				name != null &&
				postalCode != null &&
				town != null)
			{
				try {
					a.setNumber(Integer.parseInt(number));
					a.setPostalCode(Integer.parseInt(postalCode));
					a.setName(name);
					a.setTown(town);
					a.save();
				}
				catch (NumberFormatException e) {}
			}
			return null;
		}
		else
			return "An error has occured. Please retry later";
	}

	public String getLoginFromMember(int seller) {
		return factory.makeMember().getLogin(seller);
	}
	
	public int getSellerFromLogin(int userId) {
		return factory.makeMember().getSeller(userId);
	}
	
	public int getIdFromLogin(String login) {
		return factory.makeUser().loadId(login);
	}

	public List<User> getUsers() 
	{
		return factory.makeUser().load();
	}
	public User getUser(int id, String login)
	{
		User u = factory.makeUser();
		if(id > 0)
		{
			return u.loadProfileById(id);
		}
		else if(login != null && !login.isEmpty())
		{
			return u.loadProfileByLogin(login);
		}
		else
			return null;
	}

	public boolean handleManagerAdd(String user)
	{
		return factory.makeManager().save(user);
	}

	public boolean handleManagerDelete(String userName)
	{
		return factory.makeManager().delete(userName);
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
	
	public int getMemberID(int userId){
		Member m = factory.makeMember();
		return m.getMemberId(userId);
	}
	public String getSpeakerID(int userID){
		Speaker s = factory.makeSpeaker();
		int id = s.getIdFromSpeaker(userID);
		if(id == 0)
			return null;
		return ""+id;
	}
	
	public List<Object> getManagers() 
	{
		return factory.makeManager().load();	
	}

	public List<Object> getCategories() 
	{
		return factory.makeActivityCategory().load();		
	}

	public SubscriptionPaymentList getPaymentsFromMember(int idMember) {
		SubscriptionPaymentList pl = factory.makeSubscriptionPaymentList();
		pl.load(idMember);
		return pl;
	}

	public SubscriptionPayment getLastSubscriptionPayment(int idMember) {
		return factory.makeSubscriptionPayment().getLastSubscriptionPayment(idMember);
	}

	public int registerMember(int userId) {
		Member m = factory.makeMember();
		
		// on recupere la date du jour
		String registrationDate;
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		Date date = new Date();
		registrationDate = formater.format( date );
		
		return m.save(userId, registrationDate);
	}

	public void createSubscriptionPayment(int idMember, int amountPaid, String paymentType) {
		SubscriptionPayment sp = factory.makeSubscriptionPayment();
		
		// on recupere la date du jour
		String paymentDate;
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		Date date = new Date();
		paymentDate = formater.format( date );
		
		sp.save(amountPaid, paymentDate, paymentType, idMember);	
	}
	
	   public boolean verifyToken(String login, String token) {
		   if(login == null || token == null)
			   return false;
	    	return factory.makeUser().check(login,token);
	    }
}
