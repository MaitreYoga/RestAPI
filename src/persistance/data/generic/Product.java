package persistance.data.generic;


public abstract class Product {
	
	private int idProd;
	private String name;
	private String brand;
	private int price;
	private int quantityAvailable;
	private int memberPrice;
	private int productCat;
	private int seller;
	private String urlImage;

    public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Product(){
    	this.idProd=0;
    	this.name="";
    	this.brand="";
    	this.price=0;
    	this.quantityAvailable=0;
    	this.memberPrice=0;
    	this.productCat=0;
    	this.seller=0;
    }

    public int getIdProd() {
		return idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(int memberReduction) {
		this.memberPrice= memberReduction;
	}

	public int getProductCat() {
		return productCat;
	}

	public void setProductCat(int productCat) {
		this.productCat = productCat;
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public abstract String save();
	public abstract void load(int product);
	public abstract void update();
	public abstract void updateQuantityAvailable(int idProd, int newQuant);
    
    @Override
    public String toString(){
    	return this.getBrand()+" - "+this.getName();
    }

}
