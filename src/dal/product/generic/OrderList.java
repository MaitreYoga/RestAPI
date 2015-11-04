package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderList {

	private List<Order> orders;
	
	public OrderList(){
		orders = new ArrayList<Order>();
	}
	
	public int size(){
		return orders.size();
	}
	
	public void add(Order order){
		orders.add(order);
	}
	
	public Order get(int index){
		return orders.get(index);
	}
	
	public abstract void load(int userId);
}
