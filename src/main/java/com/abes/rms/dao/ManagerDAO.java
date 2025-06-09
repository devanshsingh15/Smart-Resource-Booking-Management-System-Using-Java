package com.abes.rms.dao;

import com.abes.rms.dto.ResourceMan;

public interface ManagerDAO {
	public boolean addUser(String id, String pass, String name, String email);
	public ResourceMan getUser(String userID, String pass);
	public boolean isManagerPresent(String userID, String pass);
}
