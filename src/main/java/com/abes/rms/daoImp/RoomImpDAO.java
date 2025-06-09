package com.abes.rms.daoImp;

import java.util.ArrayList;

import com.abes.rms.dao.RoomDAO;
import com.abes.rms.dto.*;
import com.abes.rms.util.CollectionsUtil;

public class RoomImpDAO implements RoomDAO {
	
	
	@Override
	public boolean addRoom(Room room) {
		if (!CollectionsUtil.rooms.contains(room)) {
	        CollectionsUtil.rooms.add(room);
	        return true;  // added successfully
	    }
	    return false;  // already present
	}
	
	@Override
	public boolean editRoomCost(String id, double cost) {
		for (Room room : CollectionsUtil.rooms) {
	        if (room.getId().equals(id)) {
	            room.setCostPerHour(cost);
	            return true;
	        }
	    }
	    return false; // Room not found
	}
	
	@Override
	public boolean editRoomType(String id, String type) {
		for (Room room : CollectionsUtil.rooms) {
	        if (room.getId().equals(id)) {
	            room.setType(type);
	            return true;
	        }
	    }
	    return false; // Room not found
	}
	
	@Override
	public Room getRoom(String id) {
		for (Room room : CollectionsUtil.rooms) {
	        if (room.getId().equals(id)) {
	            return room;
	        }
	    }
	    return null; // Room not found
	}
	
	@Override
	public ArrayList<Room> getAvailRooms() {
		ArrayList<Room> availableRooms = new ArrayList<>();
	    for (Room room : CollectionsUtil.rooms) {
	        if (room.isAvailable()) {
	            availableRooms.add(room);
	        }
	    }
	    return availableRooms;
	}

	@Override
	public boolean updateRoomStatus(Room r, boolean status) {
		for (Room room : CollectionsUtil.rooms) {
	        if (room.equals(r)) {
	            room.setAvailable(status);
	            return true;
	        }
	    }
	    return false; // Room not found
	}

	@Override
	public boolean deleteRoom(Room room) {
		
		for (Room r : CollectionsUtil.rooms) {
	        if (r.equals(room) && r.isAvailable()) {
	        	CollectionsUtil.rooms.remove(r);
	            return true;
	        }
	    }
	    return false; // Room not available
		
	}

	@Override
	public ArrayList<Room> getAllRooms() {
		return CollectionsUtil.rooms; 
	}

	@Override
	public ArrayList<Room> getBookedRooms() {
		ArrayList<Room> bookedRooms = new ArrayList<>();
	    for (Room room : CollectionsUtil.rooms) {
	        if (!room.isAvailable()) {
	            bookedRooms.add(room);
	        }
	    }
	    return bookedRooms;
	}

	@Override
	public boolean isValid(Room room) {
		 return CollectionsUtil.rooms.contains(room);
	}

	@Override
	public boolean isavailable(Room room) {
		for (Room r : CollectionsUtil.rooms) {
	        if (r.equals(room)) {
	            return r.isAvailable();
	        }
	    }
	    return false; // Room not found
	}
}
