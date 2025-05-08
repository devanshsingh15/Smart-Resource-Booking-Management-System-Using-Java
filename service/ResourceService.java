package project.SRBMS.service;

import java.util.Iterator;

import project.SRBMS.entity.Booking;
import project.SRBMS.entity.Room;
import project.SRBMS.repository.Database;

public class ResourceService {

	public static void addRoom(Room room) throws InterruptedException {
		for(Room r : Database.rooms.keySet()) {
			if(r.id.equals(room.id)) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("room with same ID already present");
        		Thread.sleep(90);
				return;
			}
		}
		Database.rooms.put(room, true);
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("Room added");
		Thread.sleep(90);

	}
	
	public static void editRoom(String id, double cost) throws InterruptedException {
		for(Room r : Database.rooms.keySet()) {
			if(r.id.equals(id)) {
				r.costPerHour = cost;
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Cost updated");
        		Thread.sleep(90);
				return;
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("Room not present");
		Thread.sleep(90);
	}
	
	
	public static void deleteRoom(String id) throws InterruptedException {
		
		boolean isBooked = false;
		Room room = getRoom(id);
		for(Booking b : Database.bookRecord) {
			if(b.room.id.equals(room.id)) {
				isBooked = true;
				break;
			}
		}
		
		if(!isBooked) {
			Iterator<Room> it = Database.rooms.keySet().iterator();
		    while (it.hasNext()) {
		        Room r = it.next();
		        if (r.id.equals(id)) {
		            it.remove(); 
		            System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
		            System.out.println("Room deleted");
            		Thread.sleep(90);
		            return;
		        }
		    }
		    System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
		    System.out.println("Room not present");
    		Thread.sleep(90);
		} else {
		    System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
		    System.out.println("Room have a booking");
    		Thread.sleep(90);
		}
		
	}
	
	public static void showAllRooms() {
		for(Room room : Database.rooms.keySet()) {
			System.out.println(room);
		}
	}

	public static void showBookedRooms() throws InterruptedException {
		boolean found = false;
		for(Room room : Database.rooms.keySet()) {
			if(!Database.rooms.get(room)) {
				System.out.println(room);
				found = true;
			}
		}
		if (!found) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
	        System.out.println("No rooms are booked.");
    		Thread.sleep(90);
	    }
	}
	
	public static Room getRoom(String id) {
		for(Room room : Database.rooms.keySet()) {
			if(room.id.equals(id)) {
				return room;
			}
		}
		return null;
	}
	
	
}
