package com.abes.rms.util;

import java.util.Comparator;

import com.abes.rms.dto.Booking;

public class SortBookingById implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
