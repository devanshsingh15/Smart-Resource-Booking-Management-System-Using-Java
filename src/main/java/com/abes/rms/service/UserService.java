package com.abes.rms.service;

import java.util.ArrayList;

import com.abes.rms.daoImp.*;
import com.abes.rms.dto.*;
import com.abes.rms.exception.InvalidRoomException;

public class UserService {
	
	UserImpDAO userObj = new UserImpDAO();
	CartImpDAO cartObj = new CartImpDAO();
	RoomImpDAO roomObj = new RoomImpDAO();
	BookingImpDAO bookingObj = new BookingImpDAO();
	
	public boolean signUp(String id, String pass, String name, String email) {
		return userObj.addUser(id, pass, name, email);
	}
	
	public boolean bookRoom(String id, RegularUser user, double time) {
		for(Room room : roomObj.getAvailRooms()) {
			if(room.getId().equals(id)) {
				roomObj.updateRoomStatus(room, false);
				double cost = time * room.getCostPerHour();
				Booking booking = new Booking(user, room, time, cost);
				bookingObj.addBooking(booking); 
				user.rooms.add(room);
				room.setCount(room.getCount() + 1);
				return true;
			}
		}
		return false;
	}
	
	public boolean cancelRoom(String id, RegularUser user, Room r) {
		boolean present = false;
		for(Room rm : user.rooms) {
			if(rm.getId().equalsIgnoreCase(id)) {
				present = roomObj.updateRoomStatus(rm, true);
			}
		}
		if(present) user.rooms.remove(r);
		return present;
	}
	
	public ArrayList<Room> showBookedRooms(RegularUser user) {
		return user.rooms;
	}
	
	public boolean addToCart(RegularUser user, String id3, double time2) throws InvalidRoomException {
		Room room = roomObj.getRoom(id3);
		if(room != null) {
			Booking booking = new Booking(user, room, time2, time2*room.getCostPerHour());
			ArrayList<Booking> temp = cartObj.getCartBooking(user);
			if (temp == null) {
		        temp = new ArrayList<>();
		    }
			if(roomObj.isavailable(room)) {
				temp.add(booking);
				return cartObj.updateRoom(user, temp);
				//booked
			}
			else {
				//not available
				return false;
			}
		} else {
			throw new InvalidRoomException();
		}
	}
	
	public boolean verifyUser(String userID, String pass) {
		return userObj.isUserPresent(userID, pass);
	}
	
	public RegularUser getUser(String userID, String pass) {
		return userObj.getUser(userID, pass);
	}
	
	public RegularUser getUserByEmail(String userID, String email) {
		return userObj.getUserByEmail(userID, email);
	}
	
	public void showCart(RegularUser user) {
		ArrayList<Booking> temp = cartObj.getCart(user);
		if(temp != null) {
			double cartCost = 0;	
			for(Booking booking : temp) {
				System.out.println("RoomID: " + booking.getRoom().getId() + " Total Cost: " + booking.getTotalCost());
				cartCost += booking.getTotalCost();
			}
			System.out.println("Total cart cost: " + cartCost);
		} else {
			System.out.println("Empty cart.");
		}
	}

	public boolean removeCart(RegularUser user, String id) throws InvalidRoomException {
		Room room = roomObj.getRoom(id);
		if(room != null) {
			ArrayList<Booking> temp = cartObj.getCartBooking(user);
			if (temp.isEmpty()) {
		        //empty cart
				return false;
		    }
			else {
				for(Booking booking : temp) {
					if(booking.getRoom().getId().equals(room.getId())) {
						temp.remove(booking);
						cartObj.updateRoom(user, temp);
						return true;
					}
				}
			}
		} else {
			throw new InvalidRoomException();
		}
		return false;
	}

	public boolean makePayment(RegularUser user) {
		ArrayList<Booking> temp = cartObj.getCart(user);
		if(temp != null) {	
			for(Booking booking : temp) {
				bookRoom(booking.getRoom().getId(), user, booking.getTimeSlot());
			}
			temp.clear();
			return true;
		} 
		//empty cart
		return false;
	}
	
}
