package com.abes.rms.util;

import java.util.Comparator;

import com.abes.rms.dto.Booking;

public class SortBookingByDuration implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		return Double.compare(o1.getTimeSlot(), o2.getTimeSlot());
	}

}

