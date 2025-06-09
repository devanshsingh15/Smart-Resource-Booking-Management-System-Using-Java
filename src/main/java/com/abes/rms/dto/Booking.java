package com.abes.rms.dto;

import java.time.LocalDateTime;

public class Booking {
	private static int counter = 0;
	private String id;
	private RegularUser user;
	private Room room;
	private double timeSlot;
	private double totalCost;
	private LocalDateTime timeOfBooking;
	
	public Booking(RegularUser user, Room room, double timeSlot, double totalCost) {
		this.id = "" + ++counter;
		this.user = user;
		this.room = room;
		this.timeSlot = timeSlot;
		this.totalCost = totalCost;
		this.timeOfBooking = LocalDateTime.now(); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RegularUser getUser() {
		return user;
	}

	public void setUser(RegularUser user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public double getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(double timeSlot) {
		this.timeSlot = timeSlot;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}

	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	
	public String toString() {
		return "id: " + id + ", userName: " + user.getName() + ", roomID: " + room.getId() + ", duration: " + timeSlot + ", Price/hour: " + room.getCostPerHour() + ", Booking amount: " + getTotalCost() +
				", Time of Booking: " + timeOfBooking; 
	}
}