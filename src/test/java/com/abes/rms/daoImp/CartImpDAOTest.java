package com.abes.rms.daoImp;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import com.abes.rms.dao.CartDAO;
import com.abes.rms.dto.Booking;
import com.abes.rms.dto.RegularUser;
import com.abes.rms.dto.Room;
import com.abes.rms.util.CollectionsUtil;

public class CartImpDAOTest{
	private CartDAO cartDAO;
	private RegularUser user;
	private Room room;
	private ArrayList<Booking> bookings;
	
	@BeforeEach
	void setup() {
		cartDAO = new CartImpDAO();
		user = new RegularUser("101","123","satya","satya@gmail.com");
		room = new Room("302","Ac",3.0);
		bookings = new ArrayList<>();
		bookings.add(new Booking(user,room,2.0,100.0));
		
	}
	
	@Test
	void testUpdateRoom() {
		bookings.add(new Booking(user, room, 2.0,300.0));
		boolean result = cartDAO.updateRoom(user, bookings);
		Assertions.assertTrue(result, "updateroom should return true after adding to cart");
		
	}
	

	@Test
	void testGetCartBookingList() {
		CollectionsUtil.cart.put(user, bookings);
		boolean result = cartDAO.getCart(user) != null;
		Assertions.assertTrue(result,"getCart should return booking list when user exists");
	}
	
	@Test
	void testGetCartReturnNull() {
		boolean result = cartDAO.getCartBooking(user) == null;
		Assertions.assertTrue(result,"getCartBooking should return null if user not found");
		
	}
}
