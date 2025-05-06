package project.SRBMS.service;

import project.SRBMS.entity.Room;
import project.SRBMS.repository.Database;

public class ResourceService {

	public static void addRoom(Room room) {
		for(Room r : Database.rooms.keySet()) {
			if(r.id.equals(room.id)) {
				System.out.println("room with same ID already present");
				return;
			}
		}
		Database.rooms.put(room, true);
		System.out.println("Room added");

	}
	
	public static void editRoom(String id, double cost) {
		for(Room r : Database.rooms.keySet()) {
			if(r.id.equals(id)) {
				r.costPerHour = cost;
				System.out.println("Cost updated");
				return;
			}
		}
		System.out.println("Room not present");
	}
	
	
	public static void deleteRoom(String id) {
		for(Room r : Database.rooms.keySet()) {
			if(r.id.equals(id)) {
				Database.rooms.remove(r);
				System.out.println("room deleted");
				return;
			}
		}
		System.out.println("room not present");
	}
	
	public static void showAllRooms() {
		for(Room room : Database.rooms.keySet()) {
			System.out.println(room);
		}
	}

	public static void showBookedRooms() {
		for(Room room : Database.rooms.keySet()) {
			if(!Database.rooms.get(room)) {
				System.out.println(room + ", no. of room: " + Database.rooms.get(room));
			}
		}
	}
	
	
}
