package dal.product.generic;

import java.util.HashMap;
import java.util.List;

public abstract class User {
	
	private int    id;
	private String firstName;
	private String lastName;
	private String phone;
	private String mail;
	private int idAdress;
	private int    number;
	private String name;
	private int    postalCode;
	private String town;
	private String login;
	private String connectiontoken;
	private int idMember, idManager, idSpeaker, idAdministrator;
	
    public User(){

    }
    
    public abstract String check(String login);
    public abstract User load(String login, String pwd);
    public abstract int loadId(String login);
    public abstract String save(String tempPwd);
	
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getIdMember() {
    	return idMember;
    }
    
    public void setIdMember(int idMember) {
    	this.idMember = idMember;
    }
    public int getIdManager() {
    	return idManager;
    }
    
    public void setIdManager(int idManager) {
    	this.idManager = idManager;
    }
    public int getIdSpeaker() {
    	return idSpeaker;
    }
    
    public void setIdSpeaker(int idSpeaker) {
    	this.idSpeaker = idSpeaker;
    }
    public int getIdAdministrator() {
    	return idAdministrator;
    }
    
    public void setIdAdministrator(int idAdministrator) {
    	this.idAdministrator = idAdministrator;
    }
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getConnectionToken()
	{
		return this.connectiontoken;
	}
	
	public void setConnectionToken(String connectionToken)
	{
		this.connectiontoken = connectionToken;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return number + " " + name + ", " + postalCode + " " + town;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(String number){
		this.number = Integer.parseInt(number);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public int getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode){
		this.postalCode = Integer.parseInt(postalCode);
	}

	public String getTown() {
		return town;
	}
	
	
	public void setTown(String town){
		this.town = town;
	}
	
	public int getIdAdress() {
		return idAdress;
	}
	
	public void setIdAdress(int idAdress) {
		this.idAdress = idAdress;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public abstract List<User> load();

	public abstract int saveCurrentUser();

	public abstract User loadProfileById(int id);

	public abstract User loadProfileByLogin(String login);

	public abstract boolean check(String login, String token);
}