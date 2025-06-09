package com.abes.rms.util;

import java.util.Comparator;

import com.abes.rms.dto.Room;

public class SortRoomById implements Comparator<Room>{

	@Override
	public int compare(Room o1, Room o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
