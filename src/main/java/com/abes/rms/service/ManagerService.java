package com.abes.rms.service;

import java.util.ArrayList;

import com.abes.rms.daoImp.*;
import com.abes.rms.dto.*;
import com.abes.rms.exception.InvalidRoomException;

public class ManagerService {
	ManagerImpDAO manObj = new ManagerImpDAO();
	RoomImpDAO roomObj = new RoomImpDAO();
	public boolean signUp(String id, String pass, String name, String email) {
		return manObj.addUser(id, pass, name, email);
	}
	
	public boolean verifyUser(String userID, String pass) {
		return manObj.isManagerPresent(userID, pass);
	}
	
	public ResourceMan getUser(String userID, String pass) {
		return manObj.getUser(userID, pass);
	}
	
	public boolean addRoom(Room room) {
		return roomObj.addRoom(room);
	}
	
	public boolean editRoom(String id, double cost) {
		return roomObj.editRoomCost(id, cost);
	}
	
	public boolean editRoom(String id, String type) {
		return roomObj.editRoomType(id, type);
	}
	
	public boolean deleteRoom(String id) throws InvalidRoomException {
		Room room = roomObj.getRoom(id);
		if(!roomObj.isValid(room)) throw new InvalidRoomException();
		return roomObj.deleteRoom(room);
	}

	public ArrayList<Room> showAllRooms() {
		return roomObj.getAllRooms();
	}
	
	public ArrayList<Room> showBookedRooms() {
		return roomObj.getBookedRooms();
	}
}