package com.abes.rms.dao;

import com.abes.rms.dto.RegularUser;

public interface UserDAO {
	public boolean addUser(String id, String pass, String name, String email);
	
	public boolean deleteUser(RegularUser user);
	
	public boolean isUserPresent(String userID, String pass);
	
	public RegularUser getUser(String userID, String pass);

	public RegularUser getUserByEmail(String userID, String email);
}
