package com.abes.rms.service;

import java.util.ArrayList;

import com.abes.rms.daoImp.RoomImpDAO;
import com.abes.rms.dto.Room;

public class RoomService {
	RoomImpDAO roomObj = new RoomImpDAO();
	
	public ArrayList<Room> showAvailRooms() {
		return roomObj.getAvailRooms();
	}
	
	public Room getRoom(String id) {
		return roomObj.getRoom(id);
	}
	
	
}
