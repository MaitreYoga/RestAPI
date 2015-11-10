package bl.manager;

import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.omg.CORBA.OMGVMCID;

import dal.factory.Factory;
import dal.product.generic.Activity;
import dal.product.generic.Event;
import dal.product.generic.EventList;
import dal.product.generic.Inscription;
import dal.product.generic.Member;
import dal.product.generic.Period;
import dal.product.generic.Room;
import dal.product.generic.RoomList;
import dal.product.generic.Speaker;

public class EventManager 
{
    private Factory factory;
    private EventList eventList;
    private Period period;
    private Event event;
    private Speaker speaker;
    private Activity activity;
    private Room room;
    private RoomList roomList;
    private Member member;
    private Inscription inscription;
    
    public EventManager()
    {
    	factory = Factory.getInstance();
    }

    public void handleEventSearch() 
    {
    	
    }

    public Event getEvent(String chosenEvent) 
    {
    	event = factory.makeEvent();
    	event.load(chosenEvent);
    	
    	return event;
    }

    public void handleEventInscription(int userID, String chosenEvent) 
    {
    	inscription = factory.makeInscription();
    	inscription.save(userID,chosenEvent);
    }

    public List<Event> getAllEvents() 
    {
    	eventList = factory.makeEventList();
    	eventList.load();
    	System.out.println(eventList.getList());
    	return eventList.getList();
    }

	public boolean finaliseInscription(int memberID, int chosenEvent,int amount) 
	{
		inscription = factory.makeInscription();
		return inscription.save(memberID, chosenEvent,amount);
	}

	public String getSpeaker(String speakerID) 
	{
		speaker = factory.makeSpeaker();
		return speaker.load(speakerID);
	}

	public String getPeriod(String periodID) 
	{
		period = factory.makePeriod();
		return period.load(periodID);
	}

	public String getActivity(String activityID) 
	{
		activity = factory.makeActivity();
		return activity.load(activityID);
	}

	public String getPlace(String placeID) 
	{
		room = factory.makeRoom();
		return room.load(placeID);
	}

	public int getMember(String login) 
	{
		member = factory.makeMember();
		return member.getMemberId(login);
	}

	public int getEventID(String event) 
	{
		this.event = factory.makeEvent();
		return this.event.getID(event);
	}

	public EventList getUserEvents(int userID) {
		EventList events = factory.makeEventList();
		events.load(userID);
		return events;
	}

	public List<Room> getRooms() 
	{
		room = factory.makeRoom();
		return room.load();
	}

	public boolean deleteRoom(String roomName) 
	{
		room = factory.makeRoom();
		return room.deleteRoom(roomName);
	}

	public boolean createRoom(String roomName, int surface) 
	{
		room = factory.makeRoom();
		return room.createRoom(roomName,surface);
	}

	public String updateRoomList() 
	{
		room = factory.makeRoom();
		return room.loadList();
		
	}

	public List<Object> getAllEvents(String searchCriteria) 
	{
		eventList = factory.makeEventList();
		return eventList.loadList(searchCriteria);
	}

	public String getEvents(String searchCriteria, String choice) 
	{
		eventList = factory.makeEventList();
		return eventList.loadEvents(searchCriteria,choice);
	}

	public int deleteAccessoryFromRoom(int idRoom, int idAccessory) {
		eventList = factory.makeEventList();
		room = factory.makeRoom();
		return room.deleteAccessoryFromRoom(idRoom,idAccessory);
	}

	public int addAccessoryToRoom(int idAccessory, int idRoom) {
		eventList = factory.makeEventList();
		room = factory.makeRoom();
		return room.addAccessoryToRoom(idAccessory,idRoom);
	}

	public List<Object> getPeriods(String chosenEvent) 
	{
		period = factory.makePeriod();
		return period.loadPeriod(chosenEvent);
		
	}

	public RoomList getAllRooms() {
		roomList = factory.makeRoomList();
		roomList.load();
		return roomList;
	}

	public String createEvent(String name, String price, String startDate,String endDate, String activityID, String roomID, String speakerID) {
		event = factory.makeEvent();
		event.setName(name);
		event.setPrice(Integer.parseInt(price));
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setActivity(activityID);
		event.setRoom(roomID);
		event.setSpeaker(speakerID);
		return event.save();
	}
	
}