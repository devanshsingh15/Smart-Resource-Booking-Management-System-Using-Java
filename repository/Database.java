package project.SRBMS.repository;
import java.util.*;
import project.SRBMS.entity.*;
import project.SRBMS.entity.RegularUser;

public class Database {
	static public ArrayList<RegularUser> users = new ArrayList<RegularUser>();
	static public HashMap<Room, Boolean> rooms = new HashMap<Room, Boolean>();
	static public ArrayList<Booking> bookRecord = new ArrayList<Booking>();
	static public HashMap<RegularUser, ArrayList<Booking>> cart = new HashMap<RegularUser, ArrayList<Booking>>();
	
	static {
		rooms.put(new Room("101", "Lux", "Double", 500), true);
		rooms.put(new Room("102", "Queen", "Double + Single", 700), true);
		rooms.put(new Room("103", "Regular", "Single", 400), true);
		rooms.put(new Room("201", "Lux", "Single", 300), true);
		rooms.put(new Room("302", "Queen", "Double", 550), true);
		rooms.put(new Room("202", "Regular", "Single", 250), true);		
	}
	
	public static void showUsers() {
		for(RegularUser user : users) {
			System.out.println(user);
		}
	}
	
	public static void showAllRooms() {
		for(Room room : rooms.keySet()) {
			System.out.println(room);
		}
	}
}
