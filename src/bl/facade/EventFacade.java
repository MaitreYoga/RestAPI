package bl.facade;

import java.util.ArrayList;
import java.util.List;
import bl.manager.ActivityCategoryManager;
import bl.manager.ActivityManager;
import bl.manager.EventManager;
import bl.manager.ManagerManager;
import bl.manager.SpeakerManager;
import bl.manager.UserManager;
import dal.product.generic.Accessory;
import dal.product.generic.AccessoryList;
import dal.product.generic.ActivityCategoryList;
import dal.product.generic.ActivityList;
import dal.product.generic.Event;
import dal.product.generic.EventList;
import dal.product.generic.ManagerList;
import dal.product.generic.Room;
import dal.product.generic.RoomList;
import dal.product.generic.SpeakerList;
import bl.manager.AccessoryManager;

public class EventFacade 
{
	private UserManager userManager;
    private SpeakerManager speakerManager;
    private ManagerManager managerManager;

    private ActivityCategoryManager activityCategoryManager;
    private ActivityManager activityManager;
    private EventManager eventManager;
    private AccessoryManager accessoryManager;

    private static EventFacade instance;
    
	public static EventFacade instance() {
		if(instance == null)
			instance = new EventFacade();
		return instance;
	}
	
    private EventFacade()
    {
    	userManager = new UserManager();
    	speakerManager = new SpeakerManager();
    	managerManager = new ManagerManager();

    	activityCategoryManager = new ActivityCategoryManager();
    	activityManager = new ActivityManager();
    	eventManager = new EventManager();
    	accessoryManager = new AccessoryManager();
    }

	public void handleEventInscription(int userId, String chosenEvent) 
	{
		eventManager.handleEventInscription(userId, chosenEvent);
	}

	public EventList getUserEvents(int userID) {
		return eventManager.getUserEvents(userID);
	}

	public SpeakerList getAllSpeakers() {
		return speakerManager.getAllSpeakers();
	}

	public ActivityList getAllActivities() {
		return activityManager.getAllActivities();
	}

	public ManagerList getAllManagers() {
		return managerManager.getAllManagers();
	}

	public AccessoryList getAllAccessories() {
		return accessoryManager.getAllAccessories();
	}
	
	public ArrayList<Accessory> getAccessoriesByRoom(int room){
		return accessoryManager.getAccessoriesByRoom(room);
	}

	public Accessory addAccessory(String description) {
		return accessoryManager.addAccessory(description);
	}

	public List<Object> getCategories() 
	{
		return userManager.getCategories();		
	}
	public String updateCategoryList() {
		return activityCategoryManager.updateCategoryList();
	}

	public boolean deleteActivityCategory(String activityCategoryName) {
		return activityCategoryManager.deleteActivityCategory(activityCategoryName);
	}

	public boolean createActivityCategory(String activityCategoryName) {
		return activityCategoryManager.createActivityCategory(activityCategoryName);
	}

	public String getSpeaker(String speakerID) {
		return eventManager.getSpeaker(speakerID);
	}

	public String getPeriod(String periodID) {
		return eventManager.getPeriod(periodID);
	}

	public String getActivity(String activityID) {
		return eventManager.getActivity(activityID);
	}

	public String getPlace(String placeID) {
		return eventManager.getPlace(placeID);
	}

	public List<Event> getAllEvents() 
	{
		return eventManager.getAllEvents();
	}

	public List<Object> getPeriods(String chosenEvent) 
	{
		return eventManager.getPeriods(chosenEvent);
	}

	public Event getEvent(int eventId) {
		return eventManager.getEvent(eventId);
	}

	public boolean finaliseInscription(int memberID, int eventID,int amount) {
		return eventManager.finaliseInscription(memberID, eventID, amount);
	}

	public int getMember(int userID) {
		return eventManager.getMember(userID);
	}

	public List<Room> getRooms() 
	{
		return eventManager.getRooms();
		
	}

	public boolean deleteRoom(String roomName) 
	{
		return eventManager.deleteRoom(roomName);
	}

	public boolean createRoom(String roomName, int surface) 
	{
		return eventManager.createRoom(roomName,surface);
	}

	public String updateRoomList() 
	{
		return eventManager.updateRoomList();
		
	}

	public void addActivity(String name) {
		activityManager.addActivity(name);
	}

	public ActivityCategoryList getAllActivityCategories() {
		return activityCategoryManager.getAllActivityCategories();
	}

	public List<Object> getAllEvents(String searchCriteria) 
	{
		return eventManager.getAllEvents(searchCriteria);
	}

	public String getEvents(String searchCriteria, String choice) 
	{
		return eventManager.getEvents(searchCriteria,choice);
	}
	
	public int deleteAccessoryFromRoom(int idRoom, int idAccessory){
		return eventManager.deleteAccessoryFromRoom(idRoom,idAccessory);
	}

	public int addAccessoryToRoom(int idAccessory, int idRoom) {
		return eventManager.addAccessoryToRoom(idAccessory,idRoom);
		
	}
	public RoomList getallRooms() {
		return eventManager.getAllRooms();
	}

	public String createEvent(String name, String price, String startDate,String endDate, String activityID, String roomID, String speakerID) {
		return eventManager.createEvent(name,price,startDate,endDate,activityID,roomID,speakerID);
	}

}
