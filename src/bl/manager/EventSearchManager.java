package bl.manager;

import persistance.factory.Factory;
import persistance.data.generic.Event;

public class EventSearchManager {
	private Factory factory;
    public Event user;
    
    public EventSearchManager() {
    	factory = Factory.getInstance();
    }

    public void handleEventSearch() {
    }

}
