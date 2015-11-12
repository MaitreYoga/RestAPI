package dal.product.generic;

public abstract class Address {
	
	private int id;
	private int number;
	private String name;
	private int postalCode;
	private String town;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getPostalCode(){
		return postalCode;
	}
	
	public void setPostalCode(int postalCode){
		this.postalCode = postalCode;
	}
	
	public String getTown(){
		return town;
	}
	
	public void setTown(String town){
		this.town = town;
	}
	
	public abstract void save();
	public abstract void load(int idAdress);
	public abstract void update();
}
