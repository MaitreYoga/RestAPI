package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;


public abstract class OrderLineList {
	
	private List<OrderLine> ordersL;
	
	public OrderLineList(){
		ordersL = new ArrayList<OrderLine>();
	}

	public abstract void load(int idOrder);
	
	public void add(OrderLine orderL){
		ordersL.add(orderL);
	}

	public int size() {
		return ordersL.size();
	}

	public OrderLine get(int index) {
		return ordersL.get(index);
	}
	
}
