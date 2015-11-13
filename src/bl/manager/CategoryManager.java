package bl.manager;

import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Product;
import dal.product.generic.ProductCategory;

public class CategoryManager 
{
    public Factory factory;

    public CategoryManager(){
    	factory = Factory.getInstance();
    }
    
    public List<ProductCategory> getPrimaryCategories() {
    	ProductCategory productCategory = factory.makeCategory();
    	productCategory.loadPrimaryCategoryList();
    	return productCategory.getSubCategoryList();
    }

    public List<ProductCategory> getSubCategoriesByCat(int parentCat) {
    	ProductCategory productCategory = factory.makeCategory();
    	productCategory.loadSubCategoryList(parentCat);
    	return productCategory.getSubCategoryList();
    }

	public List<Product> getProductsByCat(int parentCat) {
		ProductCategory productCategory = factory.makeCategory();
    	productCategory.loadProductList(parentCat);
    	return productCategory.getProductList();
	}

	public String createCategory(String nameCat, int parentCat) {
		ProductCategory productCategory = factory.makeCategory();
		productCategory.setLibCat(nameCat);
		productCategory.setParentCat(parentCat);
		return productCategory.save();
	}

	public int updateCatName(String catName, int catId) {
		ProductCategory productCategory = factory.makeCategory();
		productCategory.setLibCat(catName);
		productCategory.setIdCategory(catId);
		return productCategory.update();
	}

	public int delete(int catId) {
		ProductCategory productCategory = factory.makeCategory();
		productCategory.setIdCategory(catId);
		return productCategory.delete(catId);
	}

}
