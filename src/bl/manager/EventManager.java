package bl.manager;

import java.util.List;

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
    
    public EventManager()
    {
    	factory = Factory.getInstance();
    }

    public void handleEventSearch() 
    {
    	
    }

    public Event getEvent(String chosenEvent) 
    {
    	Event event = factory.makeEvent();
    	event.load(chosenEvent);
    	
    	return event;
    }

    public void handleEventInscription(int userID, String chosenEvent) 
    {
    	Inscription inscription = factory.makeInscription();
    	inscription.save(userID,chosenEvent);
    }

    public List<Event> getAllEvents() 
    {
    	EventList eventList = factory.makeEventList();
    	eventList.load();
    	return eventList.getList();
    }

	public boolean finaliseInscription(int memberID, int chosenEvent,int amount) 
	{
		Inscription inscription = factory.makeInscription();
		return inscription.save(memberID, chosenEvent,amount);
	}

	public String getSpeaker(String speakerID) 
	{
		Speaker speaker = factory.makeSpeaker();
		return speaker.load(speakerID);
	}

	public String getPeriod(String periodID) 
	{
		Period period = factory.makePeriod();
		return period.load(periodID);
	}

	public String getActivity(String activityID) 
	{
		Activity activity = factory.makeActivity();
		return activity.load(activityID);
	}

	public String getPlace(String placeID) 
	{
		Room room = factory.makeRoom();
		return room.load(placeID);
	}

	public int getMember(int userId) 
	{
		Member member = factory.makeMember();
		return member.getMemberId(userId);
	}

	public int getEventID(String event) 
	{
		Event e = factory.makeEvent();
		return e.getID(event);
	}

	public EventList getUserEvents(int userID) {
		EventList events = factory.makeEventList();
		events.load(userID);
		return events;
	}

	public List<Room> getRooms() 
	{
		Room room = factory.makeRoom();
		return room.load();
	}

	public boolean deleteRoom(String roomName) 
	{
		Room room = factory.makeRoom();
		return room.deleteRoom(roomName);
	}

	public boolean createRoom(String roomName, int surface) 
	{
		Room room = factory.makeRoom();
		return room.createRoom(roomName,surface);
	}

	public String updateRoomList() 
	{
		Room room = factory.makeRoom();
		return room.loadList();
		
	}

	public List<Object> getAllEvents(String searchCriteria) 
	{
		EventList eventList = factory.makeEventList();
		return eventList.loadList(searchCriteria);
	}

	public String getEvents(String searchCriteria, String choice) 
	{
		EventList eventList = factory.makeEventList();
		return eventList.loadEvents(searchCriteria,choice);
	}

	public int deleteAccessoryFromRoom(int idRoom, int idAccessory) {
		Room room = factory.makeRoom();
		return room.deleteAccessoryFromRoom(idRoom,idAccessory);
	}

	public int addAccessoryToRoom(int idAccessory, int idRoom) {
		Room room = factory.makeRoom();
		return room.addAccessoryToRoom(idAccessory,idRoom);
	}

	public List<Object> getPeriods(String chosenEvent) 
	{
		return factory.makePeriod().loadPeriod(chosenEvent);
		
	}

	public RoomList getAllRooms() {
		RoomList roomList = factory.makeRoomList();
		roomList.load();
		return roomList;
	}

	public String createEvent(String name, String price, String startDate,String endDate, String activityID, String roomID, String speakerID) {
		Event event = factory.makeEvent();
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