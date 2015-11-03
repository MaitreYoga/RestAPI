package bl.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import persistance.data.generic.Accessory;
import persistance.data.generic.AccessoryList;
import persistance.data.generic.ActivityCategoryList;
import persistance.data.generic.ActivityList;
import persistance.data.generic.Event;
import persistance.data.generic.EventList;
import persistance.data.generic.ManagerList;
import persistance.data.generic.Room;
import persistance.data.generic.RoomList;
import persistance.data.generic.SpeakerList;
import bl.manager.ActivityCategoryManager;
import bl.manager.ActivityManager;
import bl.manager.EventManager;
import bl.manager.ManagerManager;
import bl.manager.SpeakerManager;
import bl.manager.UserManager;
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

    public EventFacade()
    {
    	userManager = new UserManager();
    	speakerManager = new SpeakerManager();
    	managerManager = new ManagerManager();

    	activityCategoryManager = new ActivityCategoryManager();
    	activityManager = new ActivityManager();
    	eventManager = new EventManager();
    	accessoryManager = new AccessoryManager();
    }

	public String handleEventInscription(String chosenEvent) 
	{
		int userID = userManager.getUserID();
		eventManager.handleEventInscription(userID, chosenEvent);
		return userManager.getUserLogin();
	}

	public EventList getUserEvents() {
		int userID = userManager.getUserID();
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

	public List<Object> getAllEvents() 
	{
		return eventManager.getAllEvents();
	}

	public List<Object> getPeriods(String chosenEvent) 
	{
		return eventManager.getPeriods(chosenEvent);
	}

	public Event getEvent(String chosenEvent) {
		return eventManager.getEvent(chosenEvent);
	}

	public boolean finaliseInscription(int memberID, int eventID,int amount) {
		return eventManager.finaliseInscription(memberID, eventID, amount);
	}

	public int getEventID(String event) {
		return eventManager.getEventID(event);
	}

	public int getMember(String userID) {
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
