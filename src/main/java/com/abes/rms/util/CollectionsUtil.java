package com.abes.rms.util;

import java.util.*;

import com.abes.rms.dto.*;

public class CollectionsUtil {
	static public Admin admin = new Admin("admin", "Admin1", "Admin", "admin@gmail.com");
	
	static public ArrayList<RegularUser> users = new ArrayList<RegularUser>();
	
	static public ArrayList<ResourceMan> managers = new ArrayList<ResourceMan>();
	
	static public ArrayList<Room> rooms = new ArrayList<Room>();
	
	static public ArrayList<Booking> bookRecord = new ArrayList<Booking>();
	
	static public HashMap<RegularUser, ArrayList<Booking>> cart = new HashMap<RegularUser, ArrayList<Booking>>();
	
	static {
		rooms.add(new Room("101", "AC", 1000));
		rooms.add(new Room("102", "Non-AC", 700));
		rooms.add(new Room("103", "Non-AC", 700));
		rooms.add(new Room("201", "AC", 1000));
		rooms.add(new Room("202", "Non-AC", 700));
		
		users.add(new RegularUser("dev", "Dev1", "Devansh", "devanshdmp15@gmail.com"));
		
		managers.add(new ResourceMan("tush", "Tush1", "Tushar", "tushar@gmail.com"));
		
	}
	
}
