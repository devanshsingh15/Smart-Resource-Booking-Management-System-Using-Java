package project.SRBMS.service;

import project.SRBMS.entity.Booking;
import project.SRBMS.entity.Room;
import project.SRBMS.repository.Database;

public class ReportService {
	public static void maxBookedRoom() {
		int maxi = 0;
		Room temp = null;
		for(Room room : Database.rooms.keySet()) {
			if(room.count > maxi) {
				maxi = Math.max(maxi, room.count);
				temp = room;
			}
		}
		if(temp != null) {
			System.out.println("room " + temp.name + " with roomID " + temp.id + " is booked " + maxi + " times.");
		} else {
			System.out.println("no rooms booked yet");
		}
	}
	
	public static int totalBooking() {
		return Database.bookRecord.size();
	}
	
	public static double revenue() {
		double result = 0;
		for(Booking booking : Database.bookRecord) {
			result += booking.totalCost;
		}
		return result;
	}
}
