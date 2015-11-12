package bl.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dal.factory.Factory;
import dal.product.generic.Member;
import dal.product.generic.Order;
import dal.product.generic.OrderLine;
import dal.product.generic.OrderLineList;
import dal.product.generic.OrderList;
import dal.product.generic.Product;
import dal.product.generic.ProductLine;
import dal.product.generic.ProductLineList;

public class OrdersManager {

	private Factory factory;
	private Product product;
	private Order order;
	private OrderLine orderL;
	private OrderLineList orderLineL;
	private Member member;
	private OrderList orders;
	
    public OrdersManager(){
    	factory = Factory.getInstance();
    }
	
	public void createOrders(int userId, ProductLineList prods, List<Integer> sellers) {
		order = factory.makeOrder();
		
		// on supprime les doublons de sellers
		boolean test = false;
		List<Integer> sellersU = new ArrayList<Integer>();
		sellersU.add(sellers.get(0));
		for (int k = 1; k<sellers.size(); k++){
			for (int y = 0; y<sellersU.size(); y++){
				if (sellers.get(k).equals(sellersU.get(y))){
					test = true;
				}
			}
			if (!test){
				sellersU.add(sellers.get(k));
			}
			test=false;
		}
		
		for (int i = 0;i<sellersU.size();i++){
			// on recupere la date du jour
			String orderDate;
			String format = "dd/MM/yy";
			SimpleDateFormat formater = new SimpleDateFormat( format ); 
			Date date = new Date();
			orderDate = formater.format( date );
			
			int idOrder = order.save(userId, "W", orderDate);
			
			List<ProductLine> productsS = new ArrayList<ProductLine>();
			for (int j = 0; j<prods.size(); j++){
				if (getSeller(prods.get(j).getIdProduct()).equals(sellersU.get(i))){
					productsS.add(prods.get(j));
				}
			}
			orderL = factory.makeOrderLine();
			orderL.save(productsS, idOrder);
		}
	}
	
	public String getLoginFromMember(int seller) {
		member = factory.makeMember();
		return member.getLogin(seller);
	}
	
	public String getSeller(int idProduct){
		product = factory.makeProduct();
		product.load(idProduct);
		return getLoginFromMember(product.getSeller());
	}

	public OrderList getAllOrders(int userId) {
		orders = factory.makeOrderList();
		orders.load(userId);
		return orders;
	}

	public OrderLineList getProductsFromOrder(int idOrder) {
		orderLineL = factory.makeOrderLineList();
		product = factory.makeProduct();
		orderLineL.load(idOrder);
		return orderLineL;
	}

	public String getBuyerFromOrder(int idOrder) {
		order = factory.makeOrder();
		order.load(idOrder);
		return order.getBuyer();
	}

	public void updateStateOrder(int idOrder, String state) {
		order = factory.makeOrder();
		order.updateStateOrder(idOrder, state);
	}

	public int getAmountOfOrder(OrderLineList ordersLines) {
		product = factory.makeProduct();
		
		int amount = 0;
		
		for (int i = 0; i < ordersLines.size(); i++){
			product.load(ordersLines.get(i).getIdProduct());
			amount = amount + (ordersLines.get(i).getQuantity() * product.getPrice());
		}
		
		return amount;
		
	}

	public OrderLineList getOrdersLines(int idOrder) {
		orderLineL = factory.makeOrderLineList();
		orderLineL.load(idOrder);
		return orderLineL;
	}

	public int getAmountOfOrderMember(OrderLineList ordersLines) {
		product = factory.makeProduct();
		
		int amount = 0;
		String priceTest;
		for (int i = 0; i < ordersLines.size(); i++){
			product.load(ordersLines.get(i).getIdProduct());
			priceTest = Integer.toString(product.getMemberPrice());
			if (!priceTest.equals("0")){
				amount = amount + (ordersLines.get(i).getQuantity() * product.getMemberPrice());
			} else{
				amount = amount + (ordersLines.get(i).getQuantity() * product.getPrice());
			}
			
		}
		
		return amount;
	}

}
