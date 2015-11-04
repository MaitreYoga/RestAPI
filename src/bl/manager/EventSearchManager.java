package bl.manager;

import dal.factory.Factory;
import dal.product.generic.Event;

public class EventSearchManager {
	private Factory factory;
    public Event user;
    
    public EventSearchManager() {
    	factory = Factory.getInstance();
    }

    public void handleEventSearch() {
    }

}
