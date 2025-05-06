package project.SRBMS.service;

import project.SRBMS.entity.Booking;
import project.SRBMS.repository.Database;

public class BookingService {
	public static void showAllBookings() {
		for(Booking booking : Database.bookRecord) {
			System.out.println(booking);
		}
	}
}
