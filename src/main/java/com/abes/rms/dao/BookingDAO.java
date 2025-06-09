package com.abes.rms.dao;

import java.util.ArrayList;

import com.abes.rms.dto.Booking;

public interface BookingDAO {
	public boolean addBooking(Booking booking);
	public ArrayList<Booking> showBookings();
	public int getNoOfBookings();
	public double calcRevenue();
}

