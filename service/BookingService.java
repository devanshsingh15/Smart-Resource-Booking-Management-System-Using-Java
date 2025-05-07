package project.SRBMS.service;

import project.SRBMS.entity.Booking;
import project.SRBMS.repository.Database;

public class BookingService {
	public static void showAllBookings() {
		if (Database.bookRecord.isEmpty()) {
			System.out.println("--------------------------------------------------------------------------");
	        System.out.println("No bookings found.");
	        return;
	    }
		for(Booking booking : Database.bookRecord) {
			System.out.println(booking);
		}
	}
}
