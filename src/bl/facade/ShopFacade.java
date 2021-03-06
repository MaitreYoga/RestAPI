package bl.facade;

import java.util.List;

import bl.manager.CategoryManager;
import bl.manager.InvoicesManager;
import bl.manager.OrdersManager;
import bl.manager.ProductManager;
import bl.manager.ShoppingCartManager;
import bl.manager.UserManager;
import dal.product.generic.InvoiceList;
import dal.product.generic.OrderLineList;
import dal.product.generic.OrderList;
import dal.product.generic.Product;
import dal.product.generic.ProductCategory;
import dal.product.generic.ProductLineList;

public class ShopFacade {
    private CategoryManager categoryManager;
    private ShoppingCartManager cartManager;
    private ProductManager productManager;
    private UserManager userManager;
    private OrdersManager orderManager;
    //private SaleManager saleManager;
    private InvoicesManager invoiceManager;
    
	private static ShopFacade instance;
	
	public static ShopFacade instance() {
		if(instance == null)
			instance = new ShopFacade();
		return instance;
	}
	
    private ShopFacade(){
    	this.categoryManager = new CategoryManager();
    	this.cartManager = new ShoppingCartManager();
    	this.productManager = new ProductManager();
    	this.userManager = new UserManager();
    	this.orderManager = new OrdersManager();
    	//this.saleManager = new SaleManager();
    	this.invoiceManager = new InvoicesManager();
    }

    public List<ProductCategory> getPrimaryCategories() {
    	return categoryManager.getPrimaryCategories();
    }

    public List<ProductCategory> getSubCategoriesByCat(int parentCat) {
    	return categoryManager.getSubCategoriesByCat(parentCat);
    }
    
    public List<Product> getProductsByCat(int parentCat) {
    	return categoryManager.getProductsByCat(parentCat);
    }
    

	public List<Product> getProducts() {
		return productManager.getProducts();
	}
    
    public void getCategory() {
    }

    public ProductLineList getAllProductInCart(int userId) {
    	return cartManager.getAllProductInCart(userId);
    }

    public void handleRemove(int product, int userId) {
    	cartManager.handleRemove(product, userId);
    }

	public void changeQuantity(int product, int quantity, int userId) {
    	cartManager.handleChangeQuantity(product, quantity, userId);
	}

	public Product getProduct(int product) {
		return productManager.getProduct(product);
	}

	public List<String> getSellers(List<Integer> idProducts) {
		List<Integer> sellers = productManager.getSellers(idProducts);
		List<String> sellersLogins = userManager.getLoginsFromMembers(sellers);
		return sellersLogins;
	}

	public void createOrders(int userId, ProductLineList prods, List<Integer> sellers) {
		orderManager.createOrders(userId, prods, sellers);
	}

	public String sellProduct(int idmember, String name, String brand, int price,
			int memberPrice, int quantity, int productCat,String url) {
			//int id = userManager.getSellerFromLogin(Session.user().getId());
			String result = productManager.save(idmember,productCat,name,brand,price,memberPrice,quantity,url);
		return result;
		
	}
	
	public OrderList getAllOrders(int userId) {
		return orderManager.getAllOrders(userId);
	}
	
	public String getSellerFromOrder(int idOrder) {
		OrderLineList orderLineL = orderManager.getProductsFromOrder(idOrder);
		int seller = productManager.getSeller(orderLineL.get(0).getIdProduct());
		String sellerLogin = userManager.getLoginFromMember(seller);
		return sellerLogin;
	}

	public String getBuyerFromOrder(int idOrder) {
		String buyer = orderManager.getBuyerFromOrder(idOrder);
		return buyer;
	}
	
	public String createCategory(String nameCat,int parentCat){
		return this.categoryManager.createCategory(nameCat,parentCat);
	}

	public int updateCatName(String catName, int catId) {
		return this.categoryManager.updateCatName(catName, catId);
	}

	public int delete(int catId) {
		return this.categoryManager.delete(catId);
		
	}

	public OrderLineList getAllProductInOrder(int idOrder) {
    	return orderManager.getProductsFromOrder(idOrder);
	}

	public void addToCart(int userId, int idProd) {
		cartManager.addToCart(idProd, userId);
	}

	public void updateStateOrder(int idOrder, String state) {
		orderManager.updateStateOrder(idOrder, state);
		
	}

	public int getAmountOfOrder(int idOrder) {
		OrderLineList ordersLines = orderManager.getOrdersLines(idOrder);
		int amount = orderManager.getAmountOfOrder(ordersLines);
		return amount;
	}

	public void createInvoice(int amountPaid, String paymentType, String paymentDate, int idOrder) {
		invoiceManager.createInvoice(amountPaid, paymentType, paymentDate, idOrder);
		
	}

	public InvoiceList getAllInvoices(int userId) {
		return invoiceManager.getAllInvoices(userId);
	}

	public void updateQuantityAvailable(int idProd, int newQuant) {
		productManager.updateQuantityAvailable(idProd, newQuant);
		
	}

	public int getAmountOfOrderMember(int idOrder) {
		OrderLineList ordersLines = orderManager.getOrdersLines(idOrder);
		int amount = orderManager.getAmountOfOrderMember(ordersLines);
		return amount;
	}

}
