package com.abes.rms.dao;

import java.util.ArrayList;

import com.abes.rms.dto.Room;

public interface RoomDAO {
	public boolean isValid(Room room);
	public ArrayList<Room> getAllRooms();
	public boolean isavailable(Room room);
	public boolean addRoom(Room room);
	public boolean deleteRoom(Room room);
	public boolean editRoomCost(String id, double cost);
	public boolean editRoomType(String id, String type);
	public Room getRoom(String id);
	public ArrayList<Room> getAvailRooms();
	public boolean updateRoomStatus(Room r, boolean status);
	public ArrayList<Room> getBookedRooms();	
}
