package persistance;

import persistance.data.generic.User;

public class Session {
	
	private static User user;

	private static int speakerID;
	private static int memberID;
	private static int managerID;
	private static int administratorID;
	
	public static void setUserForSession(User userSession){
		user = userSession;
	}
	public static User user(){
		return user;
	}
	public static int getSpeakerID() {
		return speakerID;
	}
	public static void setSpeakerID(int speakerID) {
		Session.speakerID = speakerID;
	}
	public static int getMemberID() {
		return memberID;
	}
	public static void setMemberID(int memberID) {
		Session.memberID = memberID;
	}
	public static int getManagerID() {
		return managerID;
	}
	public static void setManagerID(int managerID) {
		Session.managerID = managerID;
	}
	public static int getAdminID() {
		return administratorID;
	}
	public static void setAdminID(int administratorID) {
		Session.administratorID = administratorID;
	}
	
	public static boolean checkPermission(String permission){
		
		//return true;
		
		if(permission == null)
			return true;
		if(permission.equals("Member") && !hasPermission(memberID))
			return false;
		if(permission.equals("Speaker") && !hasPermission(speakerID))
			return false;
		if(permission.equals("Manager") && !hasPermission(managerID))
			return false;
		if(permission.equals("Administrator") && !hasPermission(administratorID))
			return false;
		return true;
	}

	private static boolean hasPermission(int id){
		return id > 0;
	}
}