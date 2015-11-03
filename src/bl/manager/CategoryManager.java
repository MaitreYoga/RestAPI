package bl.manager;

import java.util.List;

import persistance.factory.Factory;
import persistance.data.generic.Product;
import persistance.data.generic.ProductCategory;

public class CategoryManager 
{
    public ProductCategory productCategory;
    public Factory factory;

    public CategoryManager(){
    	factory = Factory.getInstance();
    }
    
    public List<ProductCategory> getPrimaryCategories() {
    	productCategory = factory.makeCategory();
    	productCategory.loadPrimaryCategoryList();
    	return productCategory.getSubCategoryList();
    }

    public void getCategory() {
    }

    public List<ProductCategory> getSubCategoriesByCat(int parentCat) {
    	productCategory = factory.makeCategory();
    	productCategory.loadSubCategoryList(parentCat);
    	return productCategory.getSubCategoryList();
    }

	public List<Product> getProductsByCat(int parentCat) {
		productCategory = factory.makeCategory();
    	productCategory.loadProductList(parentCat);
    	return productCategory.getProductList();
	}

	public String createCategory(String nameCat, int parentCat) {
		productCategory = factory.makeCategory();
		productCategory.setLibCat(nameCat);
		productCategory.setParentCat(parentCat);
		return this.productCategory.save();
	}

	public int updateCatName(String catName, int catId) {
		productCategory = factory.makeCategory();
		productCategory.setLibCat(catName);
		productCategory.setIdCategory(catId);
		return this.productCategory.update();
	}

	public int delete(int catId) {
		productCategory = factory.makeCategory();
		productCategory.setIdCategory(catId);
		return this.productCategory.delete(catId);
	}

}
