package com.abes.rms.daoImp;

import java.util.ArrayList;

import com.abes.rms.dao.BookingDAO;
import com.abes.rms.dto.Booking;
import com.abes.rms.util.CollectionsUtil;

public class BookingImpDAO implements BookingDAO {

	@Override
	public boolean addBooking(Booking booking) {
		return CollectionsUtil.bookRecord.add(booking);
	}
	
	@Override
	public ArrayList<Booking> showBookings() {
		return CollectionsUtil.bookRecord;
	}

	@Override
	public int getNoOfBookings() {
		return CollectionsUtil.bookRecord.size();
	}

	@Override
	public double calcRevenue() {
		double result = 0;
		for(Booking booking : CollectionsUtil.bookRecord) {
			result += booking.getTotalCost();
		}
		return result;
	}

}
