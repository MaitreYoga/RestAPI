package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;


public abstract class EventList {
	
	private List<Event> events;
	
	public EventList(){
		events = new ArrayList<Event>();
	}
	
	public void add(Event e){
		events.add(e);
	}
	public Event get(int index){
		return events.get(index);
	}
	public int size(){
		return events.size();
	}

	public abstract List<Object> load();

	public abstract void load(int userID);

	public abstract List<Object> loadList(String searchCriteria);

	public abstract String loadEvents(String searchCriteria, String choice);

}
