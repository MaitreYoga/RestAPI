package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;


public abstract class ProductCategory {
    /*	Instance variables */
	
	protected int idCategory;
    protected int parentCat;
	protected String libCat;
    protected List <Product> productList;
    protected List <ProductCategory> subCategoryList;

    /* 	 Constructors */
    
	public ProductCategory(){
    	this.productList = new ArrayList<Product>();
    	this.subCategoryList = new ArrayList<ProductCategory>();
    }
    
    public ProductCategory(int id, String lib){
    	this.setIdCategory(id);
    	this.setLibCat(lib);
    }
    
    /*	Getters */
    
    public ProductCategory(int id, String lib, int parent) {
    	this.setIdCategory(id);
    	this.setLibCat(lib);
    	this.setParentCat(parent);
	}

	public List<Product> getProductList() {
		return productList;
	}
    
    public List<ProductCategory> getSubCategoryList() {
		return subCategoryList;
	}


    public int getIdCategory() {
		return idCategory;
	}
    
    public String getLibCat() {
		return libCat;
	}
    
    public int getParentCat() {
		return parentCat;
	}
    
    /*	Setters */
    
    
    public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void setSubCategoryList(List<ProductCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public void setParentCat(int parentCat) {
		this.parentCat = parentCat;
	}


	public void setLibCat(String libCat) {
		this.libCat = libCat;
	}

    
    public abstract void insert();

    public abstract void load();

    public abstract int update();
    
	public abstract void loadProductList(int parentCat);

    public abstract void loadPrimaryCategoryList();
    
    public abstract void loadSubCategoryList(int parentCat);
    
	public String toString(){
    	return this.getLibCat();
    }

	public abstract String save();

	public abstract int delete(int catId);



}
