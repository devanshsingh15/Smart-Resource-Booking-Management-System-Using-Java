package com.abes.rms.dao;

import java.util.ArrayList;

import com.abes.rms.dto.*;

public interface AdminDAO {
	public boolean isAdminPresent(String userID, String pass);
	public Admin getAdmin(String userID, String pass);
	public ArrayList<RegularUser> showUsers();
}

