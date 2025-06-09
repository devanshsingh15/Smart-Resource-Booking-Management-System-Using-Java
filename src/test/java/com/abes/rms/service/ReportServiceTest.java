package com.abes.rms.service;

import com.abes.rms.daoImp.BookingImpDAO; 
import com.abes.rms.dto.Booking;
import com.abes.rms.dto.RegularUser;
import com.abes.rms.dto.Room;
import com.abes.rms.util.CollectionsUtil;

import org.junit.jupiter.api.*;

public class ReportServiceTest{
	
	private ReportService reportService;
	
	@BeforeEach
	void setUp() {
		CollectionsUtil.rooms.clear();
		CollectionsUtil.bookRecord.clear();
		CollectionsUtil.cart.clear();
		
		Room room = new Room("2","AC",200);
		room.setCount(5);
		CollectionsUtil.rooms.add(room);
		
		RegularUser user = new RegularUser("1","user1", "User123","user@gmail.com");
		Booking booking = new Booking(user,room,2.0,400.0);
		BookingImpDAO dao = new BookingImpDAO();
		dao.addBooking(booking);
		reportService = new ReportService();
	}
	
	@Test
	void testMaxBookedRoom() {
		Room result = reportService.maxBookedRoom();
		Assertions.assertEquals(5, result.getCount());
	}
	
	@Test
	void testTotalBooking() {
		int bookings = reportService.totalBooking();
		Assertions.assertEquals(1, bookings);		
	}
	
	@Test
	void testRevenue() {
		double revenue = reportService.revenue();
		Assertions.assertEquals(400, revenue);
	}
}

