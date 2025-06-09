package com.abes.rms.service;

import java.util.ArrayList;

import com.abes.rms.daoImp.*;
import com.abes.rms.dto.*;

public class AdminService {
	AdminImpDAO adminObj = new AdminImpDAO();
	BookingImpDAO bookingObj = new BookingImpDAO();
	UserImpDAO userObj = new UserImpDAO();
	
	public boolean verifyUser(String userID, String pass) {
		return adminObj.isAdminPresent(userID, pass);
	}
	public Admin getUser(String userID, String pass) {
		return adminObj.getAdmin(userID, pass);
	}
	
	public ArrayList<RegularUser> showAllUsers() {
		return adminObj.showUsers();
	}
	
	public ArrayList<Booking> showAllBookings() {
		return bookingObj.showBookings();
	}
	
	public boolean deleteUser(RegularUser user) {
		return userObj.deleteUser(user);
	}
}
