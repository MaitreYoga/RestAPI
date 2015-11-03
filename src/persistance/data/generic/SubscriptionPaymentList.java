package persistance.data.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class SubscriptionPaymentList {

	private List<SubscriptionPayment> subscriptions;
	
	public SubscriptionPaymentList(){
		subscriptions = new ArrayList<SubscriptionPayment>();
	}
	
	public int size(){
		return subscriptions.size();
	}
	
	public void add(SubscriptionPayment invoice){
		subscriptions.add(invoice);
	}
	
	public SubscriptionPayment get(int index){
		return subscriptions.get(index);
	}
	
	public abstract void load(int idMember);
}
