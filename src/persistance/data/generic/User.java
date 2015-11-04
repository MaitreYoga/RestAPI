package persistance.data.generic;

import java.util.List;

import javax.swing.JComboBox;

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
	private String pwd;
	
    public User(){
    	this.firstName="";
    	this.lastName="";
    	this.phone="";
    	this.mail="";
    	this.number = 0;
    	this.name = "";
    	this.postalCode = 0;
    	this.town = "";
    	this.login="";
    	this.pwd="";
    }
    
    public abstract String check(String login);
    public abstract String load(String login, String pwd);
    public abstract int loadId(String login);
    public abstract String save();
	
    public int getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = Integer.parseInt(id);
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public abstract List<User> load();

	public abstract int saveCurrentUser();

}